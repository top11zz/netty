package com.example.socket.netty;

import com.example.socket.util.ByteUtil;
import com.example.socket.util.DateUtil;

import java.util.*;

public class MessageChain {
    public static Map<String, Object> in(String message,int length){
        Map< String, Object> map = new HashMap<String, Object> ();
        List<String> strs = new ArrayList<String> ();
        List<String> head = new ArrayList<String> ();
        List<String> CMD = new ArrayList<String> ();
        List<String> len = new ArrayList<String> ();
        List<String> constant = new ArrayList<String> ();
        List<String> data = new ArrayList<String> ();
        List<String> random = new ArrayList<String> ();
        List<String> cs = new ArrayList<String> ();
        List<String> end = new ArrayList<String> ();
        List<List<String>> msgList = new ArrayList<List<String>> ();
//        String[] strs1 = new String [8];
//        String[] strs2 = new String [1];
//        String[] strs3 = new String [2];
//        String[] strs4 = new String [8];
//        String[] strs5 = new String [length-27];
//        String[] strs6 = new String [6];
//        String[] strs7 = new String [1];
//        String[] strs8 = new String [1];
        for (int i = 0; i < message.length(); i=i+2) {
            strs.add(message.substring(i,i+2));
        }
        for (int i = 0; i < strs.size(); i++) {
            if(i<8){
                head.add(strs.get(i));
            }else if(i<9){
                CMD.add(strs.get(i));
            }else if(i<11){
                len.add(strs.get(i));
            }else if(i<19){
                constant.add(strs.get(i));
            }else if (i < length - 8) {
                data.add(strs.get(i));
            }else if(i<length - 2){
                random.add(strs.get(i));
            }else if(i<length-1){
                cs.add(strs.get(i));
            }else if(i<length){
                end.add(strs.get(i));
            }
        }
        msgList.add(head);
        msgList.add(CMD);
        msgList.add(len);
        msgList.add(constant);
        msgList.add(data);
        msgList.add(random);
        msgList.add(cs);
        msgList.add(end);
//        int i = message.indexOf("68",1);
//        map.put("head",message.substring(0,i+2));
//        int n = message.indexOf("A2");
//        map.put("CMD",message.substring(n,n+2));
//        StringBuffer sb = null;
//        //map.put("length",sb.append(message.substring(n+4,n+6)).append(message.substring(n+2,n+4)));
//        map.put("length",message.substring(n+2, n+6));
//        map.put("constant",message.substring(n+6, n+22));
//        map.put("data",message.substring(n+22,message.length()-16));
//        map.put("random",message.substring(message.length()-16,message.length()-4));
//        map.put("cs",message.substring(message.length()-4,message.length()-2));
//        map.put("end",message.substring(message.length()-2,message.length()));
        map.put("msgList",msgList);

        return map;
    }

    public static String out(Map<String,Object> map){
        List<List<String>> msgList  = (List<List<String>>) map.get("msgList");
        List<String> dateList = new ArrayList<String> ();
        StringBuilder sb = new StringBuilder();
        sb.append(ByteUtil.listToString(msgList.get(0)));
        sb.append("22");
        sb.append("0D");
        sb.append("00");
        long i = 0x33;
        Date date = new Date();
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getSecond(date)),16)+i));
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getMinute(date)),16)+i));
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getHour(date)),16)+i));
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getWeek(date)),16)+i));
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getDay(date)),16)+i));
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getMonth(date)),16)+i));
        dateList.add(String.format("%02X",Long.valueOf(String.valueOf(DateUtil.getYear(date)).substring(2,4),16)+i));
        for (int j = 0; j < dateList.size(); j++) {
          sb.append(dateList.get(j));
        }
        for (int j = 0; j < 6; j++) {
            sb.append(String.format("%02X",Long.valueOf(new Random().nextInt(205))+i));
        }
        String cmd = sb.toString();
        long sum = 0x00;
        for (int j = 0; j < cmd.length(); j=j+2) {
            sum+=Long.valueOf(cmd.substring(j, j+2),16);
        }
        cmd=  String.format("%02x",sum);
        sb.append(cmd.substring(cmd.length()-2,cmd.length()));
        //sb.append(cmd.substring(0,2));
        sb.append("16");
        return sb.toString();
    }


}
