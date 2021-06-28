package com.yudl.sturcture.hafuman;


import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author yudongliang
 * create time 2021-06-10
 * describe : 哈夫曼编码解码
 *
 *  在这里定义编码的格式如下：
 *
 *      (这里，约定使用int的31位，最高位为0)
 *      (最高位为1，即符号标志位为1的，约定为一些敏感字段的头和尾)
 *
 *     [int:-1] 文件头（包含一些文件信息，例如后缀等等）
 *              [int:-10] 文件后缀(一个int)
 *                  [int:一个int]
 *                          unknown:0（没约定）
 *                          txt : 1
 *                          jpg : 2
 *                          ...（需要约定）
 *     [int:-1]
 *
 *     [int:-2] 字符字典：
 *              [int:] （这个为字符！），为16进制！应该解析为字符。
 *              [int:]...（字符的频率/权重：）
 *              ...
 *     [int:-2]
 *
 *     [int:-3] 编码区
 *              [int:] n个int,为正数，最高位为0。
 *              ...
 *              ...
 *              ...
 *              [int:-301] 说明下一个是最后一个int的开始。
 *              [int:] 说明剩下的bit数
 *                  [int:]...数据
 *     [int:-3]
 *
 *
 */
public class HuffmanUtils {

    /**
     * 从int获取第index位bit(0/1)
     * @param a int
     * @param index  位置，1-32
     * @return 0/1 char
     */
    public static char getBitFromInt(int a, int index) {
        if (index<0 || index>32)
            return '0';
        return (a>>index-1 & 0x00000001) == 1 ? '1' : '0';
    }

    /**
     * 把bit写入到int里面
     * @param a     int
     * @param bit   位，只能是'1' or '0'
     * @param index 位置，1-32
     */
    public static int writeBitToInt(int a,char bit,int index) {
        if (bit=='0' || index<0 || index>32)
            return a;
        return 1<<index-1 | a;
    }

    /**
     * 根据letterCode和字符取得编码字典
     * @param letterCode
     * @param c
     * @return
     */
    public static String getEncodeFromOneChar(Map<Character,String> letterCode, char c) {
        Set<Character> set = letterCode.keySet();
        if (set.contains(c)) {
            return letterCode.get(c);
        }
        return "";
    }

    /**
     * 得到编码字典
     * @param rootNode  根节点
     * @return  编码
     */
    public static Map<Character, String> getLetterCode(HufNode rootNode) {
        Map<Character, String> letterCode = new HashMap<Character, String>();
        getLetterCode(rootNode, "", letterCode);
        return letterCode;
    }

    /**
     * 先序遍历哈夫曼树,获得所有字符编码对。
     * @param rooNode 哈夫曼树根结点
     * @param suffix 编码前缀，也就是编码这个字符时，之前路径上的所有编码
     * @param letterCode 用于保存字符编码结果
     */
    private static void getLetterCode(HufNode rooNode, String suffix, Map<Character, String> letterCode) {
        if (rooNode != null) {
            if (rooNode.getLeftChild() == null && rooNode.getRightChild() == null) {
                Character character = (Character) rooNode.getObject();
                letterCode.put(character, suffix);

            }
            getLetterCode(rooNode.getLeftChild(), suffix + "0", letterCode);
            getLetterCode(rooNode.getRightChild(), suffix + "1", letterCode);
        }
    }

    /**
     * 构造Huffman树
     *      约定：左子树 <= 右子树
     * @param hufNodes 传入乱序的所有原始数据
     * @return  Huffman的头
     */
    public static HufNode buildHuffTree(List<HufNode> hufNodes) {
        PriorityQueue<HufNode> priorities = new PriorityQueue<>();
        priorities.addAll(hufNodes);

        HufNode New;
        for (int i = 0; i < hufNodes.size() - 1; i++) {
            HufNode a = priorities.poll();
            HufNode b = priorities.poll();
            New = new HufNode(a,b,a.getWeight()+b.getWeight(),null);
            if (priorities.isEmpty())
                return New;
            priorities.add(New);
        }
        return null;
    }

    /**
     * 编码
     * @param src       源文件地址
     * @param encode    编码到哪个文件
     * @param letterCode 字符字典
     * @param hufNodes 最原始的带有字符频率的Huffman结点
     * @return 是否成功
     */
    public static boolean encode(String src, String encode, Map<Character,String> letterCode, List<HufNode> hufNodes) {

        //取得后缀名
        String suffix = src.substring(src.lastIndexOf(".")+1);
        int suffixCode = 0;
        switch (suffix) {
            case "txt":
                suffixCode = 1;
                break;
            case "jpg":
                suffixCode = 2;
                break;
        }

        try(
                DataOutputStream writer = new DataOutputStream(new FileOutputStream(encode));
                FileReader reader = new FileReader(src)
        ) {
            //  [int:-1] 文件头（包含一些文件信息，例如后缀等等）
            //           [int:-10] 文件后缀(一个int)
            //                      [int:一个int]
            //                       unknown:0（没约定）
            //                       txt : 1
            //                       jpg : 2
            //                       ...（需要约定）
            //  [int:-1]
            writer.writeInt(-1);
            writer.writeInt(-10);
            writer.writeInt(suffixCode);
            writer.writeInt(-1);

            //  [int:-2] 字符字典：
            //           [int:] （这个为字符！），为16进制！应该解析为字符。
            //           [int:]...（字符的频率/权重：）
            //           ...
            //  [int:-2]
            writer.writeInt(-2);
            for (int i = 0; i < hufNodes.size(); i++) {
                writer.writeInt((Character)hufNodes.get(i).getObject());
                writer.writeInt(hufNodes.get(i).getWeight());
            }
            writer.writeInt(-2);

            //  [int:-3] 编码区
            //           [int:] n个int,为正数，最高位为0。
            //           ...
            //           ...
            //           ...
            //           [int:-301] 说明下一个是最后一个int的开始。
            //           [int:] 说明剩下的bit数
            //              [int:]...数据
            //  [int:-3]
            writer.writeInt(-3);
            int ch ;
            ArrayBlockingQueue<Character> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
            String xxx ;
            while ((ch = reader.read())!=-1) {
                char ii = (char) ch;
                xxx = getEncodeFromOneChar(letterCode, ii);
                for (int i = 0; i < xxx.length(); i++) {
                    arrayBlockingQueue.add(xxx.charAt(i));
                    if (arrayBlockingQueue.size()>=31) {
                        int pp = 0;
                        for (int j = 0; j < 31; j++) {
                            pp =  writeBitToInt(pp,arrayBlockingQueue.poll(),31-j);
                        }
                        pp = writeBitToInt(pp,'0',32);
                        writer.writeInt(pp);
                    }
                }
            }
            if (!arrayBlockingQueue.isEmpty()) {
                writer.writeInt(-301);
                writer.writeInt(arrayBlockingQueue.size());
                int pp = 0;
                int length = arrayBlockingQueue.size();
                for (int i = 0; i < length; i++) {
                    pp =  writeBitToInt(pp,arrayBlockingQueue.poll(),length-i);
                }
                writer.writeInt(pp);
            } else {
                writer.writeInt(-301);
                writer.writeInt(0);
            }
            writer.writeInt(-3);
            return true;
        } catch (IOException e) {
            File file = new File(encode);
            if (file.exists())
                file.delete();
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解码
     * @param src   待解码文件
     * @param decodeFilePath 解压的路径，注意，其是一个路径而不是一个文件。
     * @return  是否成功
     */
    public static boolean decode(String src, String decodeFilePath) {

        File file = new File(src);
        if (!file.exists()) return false;

        try (
                DataInputStream in = new DataInputStream(
                        new BufferedInputStream(
                                new FileInputStream(src)))
        ){
            int temp;

            //[-1]读取文件头
            temp = in.readInt();
            if (temp!=-1) return false;
            while ((temp = in.readInt())!=-1) {
                //读取自定义的信息
                switch (temp) {
                    case -10://后缀
                        String suffix;
                        switch (in.readInt()) {
                            case 1:
                                suffix = ".txt";
                                break;
                            case 2:
                                suffix = ".jpg";
                                break;
                            default:
                                suffix = ".unknown";
                                break;
                        }
                        decodeFilePath += "temp" + suffix;
                        break;
                }
            }

            //[-2]读取字符频率/权重
            temp = in.readInt();
            if (temp!=-2) return false;
            List<HufNode> hufNodes = new ArrayList<>();
            while ((temp = in.readInt())!=-2) {
                //读取字符
                char a = (char) temp;
                int weight = in.readInt();
                hufNodes.add(new HufNode<>(null,null,weight,a));
            }
            //建立Huffman树
            HufNode<Character> root = buildHuffTree(hufNodes);

            try(
                    DataOutputStream writer = new DataOutputStream(new FileOutputStream(decodeFilePath))
            ) {
                //[-3]读取编码 & 写入文件
                HufNode<Character> point = root;
                temp = in.readInt();
                if (temp!=-3) return false;
                while ((temp = in.readInt())!=-3) {
                    switch (temp) {
                        case -301://到最后一个int
                            int lastIntLength = in.readInt();
                            temp = in.readInt();
                            //读取 & 写入
                            for (int i = 0; i < lastIntLength; i++) {
                                point = getBitFromInt(temp,lastIntLength-i) == '1' ? point.getRightChild() : point.getLeftChild();
                                if (point.getObject()!=null) {
                                    writer.write(point.getObject());
                                    point = root;
                                }
                            }
                            break;
                        default:
                            //读取 & 写入
                            for (int i = 0; i < 31; i++) {
                                point = getBitFromInt(temp,31-i) == '1' ? point.getRightChild() : point.getLeftChild();
                                if (point.getObject()!=null) {
                                    writer.write(point.getObject());
                                    point = root;
                                }
                            }
                            break;
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据文件路径返回 Huffman 节点集合
     * @param filePath
     * @return
     */
    public static List<HufNode> getHuffNodes(String filePath) {
        List<Character> characters = null;
        List<HufNode> hufNodes = null;
        try(
                FileReader fileReader = new FileReader(filePath)
        ){
            hufNodes = new ArrayList<>();
            characters = new ArrayList<>();
            int ch ;
            while ((ch = fileReader.read())!=-1) {

                char temp = (char) ch;

                if (!characters.contains(temp)) {
                    characters.add(temp);
                    HufNode<Character> hufNode = new HufNode<>(null,null,1,temp);
                    hufNodes.add(hufNode);
                }
                else {
                    int index = characters.indexOf(temp);
                    int weight = hufNodes.get(index).getWeight();
                    hufNodes.get(index).setWeight(++weight);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileNotFoundException:"+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException:"+e.getMessage());
        }
        return hufNodes;
    }
}