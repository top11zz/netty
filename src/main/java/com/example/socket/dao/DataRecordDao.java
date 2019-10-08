package com.example.socket.dao;

import com.example.socket.entity.DataRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DataRecordDao {
    DataRecord selectByPrimaryKey(Integer stoIndex);

    public int addData(DataRecord dataRecord);

    public DataRecord checkTime(Map<String,Object> map);
}
