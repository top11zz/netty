package com.example.socket.service;

import com.example.socket.dao.DataRecordDao;
import com.example.socket.entity.DataRecord;
import com.example.socket.util.ByteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("/dataRecordService")
public class DataRecordService {

    @Resource
    private DataRecordDao dataRecordDao;

        public int addDataRecord(Map<String,Object> map) throws ParseException {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar1 = Calendar.getInstance();
            String now = df.format(calendar1.getTime());
            int min = Integer.parseInt(now.substring(14,16));
            String time = now.substring(0,14);
             if(min >= 45){
                time += "45:00";
             }else if(min >= 30){
                time += "30:00";
             }else if(min >= 15){
                time += "15:00";
             }else{
                time += "00:00";
             }
            System.out.println("time-->"+time);
             Map<String,Object> params = new HashMap<String, Object>();
             params.put("stationId",6666);
             params.put("lineId",1111);
             params.put("time",time);
            DataRecord dr = dataRecordDao.checkTime(params);
            if(dr!=null){
                return 0;
            }else{
                List<List<String>> msgList  = (List<List<String>>) map.get("msgList");
                if(msgList!=null) {
                    System.out.println("msgList-->" + msgList);
                    List<String> dataList = msgList.get(4);
                    System.out.println("dataListSize--->" + dataList.size());
                    DataRecord dataRecord = new DataRecord();
                    List<String> list10004 = new ArrayList<String>();
                    List<String> list10005 = new ArrayList<String>();
                    List<String> list10006 = new ArrayList<String>();
                    List<String> list10001 = new ArrayList<String>();
                    List<String> list10002 = new ArrayList<String>();
                    List<String> list10003 = new ArrayList<String>();
                    List<String> list10017 = new ArrayList<String>();
                    List<String> list10007 = new ArrayList<String>();
                    List<String> list10008 = new ArrayList<String>();
                    List<String> list10009 = new ArrayList<String>();
                    List<String> list10019 = new ArrayList<String>();
                    List<String> list10013 = new ArrayList<String>();
                    List<String> list10014 = new ArrayList<String>();
                    List<String> list10015 = new ArrayList<String>();
                    List<String> list10057 = new ArrayList<String>();
                    List<String> list10058 = new ArrayList<String>();
                    List<String> list10059 = new ArrayList<String>();
                    List<String> list10060 = new ArrayList<String>();
                    List<String> list10018 = new ArrayList<String>();
                    List<String> list10010 = new ArrayList<String>();
                    List<String> list10011 = new ArrayList<String>();
                    List<String> list10012 = new ArrayList<String>();
                    List<String> list10031 = new ArrayList<String>();
                    List<String> list10032 = new ArrayList<String>();
                    int num = 0;
                    long k = 0x33;
                    List<String> afterDataList = new ArrayList<String>();
                    for (int i = 0; i < dataList.size(); i++) {
                        afterDataList.add(String.format("%02X", Long.valueOf(dataList.get(i), 16) - k));
                    }
                    for (int i = 0; i < afterDataList.size(); i++) {
                        if(i<2){
                            continue;
                        } else if(2<=i && i<10){
                            list10031.add(afterDataList.get(i));
                        } else if(10<=i && i<42){
                            continue;
                        } else if(42<=i && i<50){
                            list10032.add(afterDataList.get(i));
                        } else if(50<=i && i<82){
                            continue;
                        } else if (82 <= i && i < 86) {
                            list10004.add(afterDataList.get(i));
                        } else if (86 <= i && i < 90) {
                            list10005.add(afterDataList.get(i));
                        } else if (90 <= i && i < 94) {
                            list10006.add(afterDataList.get(i));
                        } else if (94 <= i && i < 98) {
                            list10001.add(afterDataList.get(i));
                        } else if (98 <= i && i < 102) {
                            list10002.add(afterDataList.get(i));
                        } else if (102 <= i && i < 106) {
                            list10003.add(afterDataList.get(i));
                        } else if (106 <= i && i < 110) {
                            list10017.add(afterDataList.get(i));
                        } else if (110 <= i && i < 114) {
                            list10007.add(afterDataList.get(i));
                        } else if (114 <= i && i < 118) {
                            list10008.add(afterDataList.get(i));
                        } else if (118 <= i && i < 122) {
                            list10009.add(afterDataList.get(i));
                        } else if (122 <= i && i < 126) {
                            list10019.add(afterDataList.get(i));
                        } else if (126 <= i && i < 130) {
                            list10013.add(afterDataList.get(i));
                        } else if (130 <= i && i < 134) {
                            list10014.add(afterDataList.get(i));
                        } else if (134 <= i && i < 138) {
                            list10015.add(afterDataList.get(i));
                        } else if (138 <= i && i < 142) {
                            list10057.add(afterDataList.get(i));
                        } else if (142 <= i && i < 146) {
                            list10058.add(afterDataList.get(i));
                        } else if (146 <= i && i < 150) {
                            list10059.add(afterDataList.get(i));
                        } else if (150 <= i && i < 154) {
                            list10060.add(afterDataList.get(i));
                        } else if (154 <= i && i < 156) {
                            list10018.add(afterDataList.get(i));
                        } else if (156 <= i && i < 158) {
                            list10010.add(afterDataList.get(i));
                        } else if (158 <= i && i < 160) {
                            list10011.add(afterDataList.get(i));
                        } else if (160 <= i && i < 162) {
                            list10012.add(afterDataList.get(i));
                        }
                    }
                    dataRecord.setM10004(Float.parseFloat(ByteUtil.listAddFloat(list10004, 1)));
                    dataRecord.setM10005(Float.parseFloat(ByteUtil.listAddFloat(list10005, 1)));
                    dataRecord.setM10006(Float.parseFloat(ByteUtil.listAddFloat(list10006, 1)));
                    dataRecord.setM10001(Float.parseFloat(ByteUtil.listAddFloat(list10001, 3)));
                    dataRecord.setM10002(Float.parseFloat(ByteUtil.listAddFloat(list10002, 3)));
                    dataRecord.setM10003(Float.parseFloat(ByteUtil.listAddFloat(list10003, 3)));
                    dataRecord.setM10017(Float.parseFloat(ByteUtil.listAddFloat(list10017, 4)));
                    dataRecord.setM10007(Float.parseFloat(ByteUtil.listAddFloat(list10007, 4)));
                    dataRecord.setM10008(Float.parseFloat(ByteUtil.listAddFloat(list10008, 4)));
                    dataRecord.setM10009(Float.parseFloat(ByteUtil.listAddFloat(list10009, 4)));
                    dataRecord.setM10019(Float.parseFloat(ByteUtil.listAddFloat(list10019, 4)));
                    dataRecord.setM10013(Float.parseFloat(ByteUtil.listAddFloat(list10013, 4)));
                    dataRecord.setM10014(Float.parseFloat(ByteUtil.listAddFloat(list10014, 4)));
                    dataRecord.setM10015(Float.parseFloat(ByteUtil.listAddFloat(list10015, 4)));
                    dataRecord.setM10057(Float.parseFloat(ByteUtil.listAddFloat(list10057, 4)));
                    dataRecord.setM10058(Float.parseFloat(ByteUtil.listAddFloat(list10058, 4)));
                    dataRecord.setM10059(Float.parseFloat(ByteUtil.listAddFloat(list10059, 4)));
                    dataRecord.setM10060(Float.parseFloat(ByteUtil.listAddFloat(list10060, 4)));
                    dataRecord.setM10018(Float.parseFloat(ByteUtil.listAddFloat(list10018, 3)));
                    dataRecord.setM10010(Float.parseFloat(ByteUtil.listAddFloat(list10010, 3)));
                    dataRecord.setM10011(Float.parseFloat(ByteUtil.listAddFloat(list10011, 3)));
                    dataRecord.setM10012(Float.parseFloat(ByteUtil.listAddFloat(list10012, 3)));
                    dataRecord.setM10031(Float.parseFloat(ByteUtil.listAddFloat(list10031,2)));
                    dataRecord.setM10032(Float.parseFloat(ByteUtil.listAddFloat(list10032,2)));
                    dataRecord.setStoLineId(1111);
                    dataRecord.setStoStationId(6666);
                    dataRecord.setStoSyncTime(time);
                    System.out.println("dataRecord--time>" + dataRecord.getStoSyncTime());
                    int r = dataRecordDao.addData(dataRecord);
                    return r;
                }
                else return 0;
            }

        }
}
