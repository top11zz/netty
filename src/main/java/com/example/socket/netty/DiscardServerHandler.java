package com.example.socket.netty;
import	java.security.cert.PKIXRevocationChecker.Option;

import com.example.socket.service.BaseService;
import com.example.socket.service.DataRecordService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.ws.handler.HandlerResolver;
import java.text.ParseException;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelHandlerAdapter {
    @Resource
    private BaseService baseService;
    @Resource
    private DataRecordService dataRecordService;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    try {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        result.readBytes(result1);
        String resultStr = Hex.encodeHexString(result1);
//        System.out.println("Client byte said:" + result1);
        System.out.println("Client said:" + resultStr);
        Map<String, Object> map = MessageChain.in(resultStr,result1.length);
        int res = dataRecordService.addDataRecord(map);
        System.out.println("处理的结果："+res);
        // 释放资源，这行很关键
       // result.release();
        String response =  MessageChain.out(map);
        // 在当前场景下，发送的数据必须转换成ByteBuf数组
        ByteBuf encoded = ctx.alloc().buffer(2 * response.length());
        System.out.println("response said:" + response);
        encoded.writeBytes(Hex.decodeHex(response));
        ctx.write(encoded);
        ctx.flush();

    } catch (DecoderException | ParseException e) {
    //} catch (DecoderException e) {
        e.printStackTrace();
    } finally {
        ReferenceCountUtil.release(msg);
    }
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 出现异常就关闭
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println(currentChannel.remoteAddress()+" 加入\n");
        super.handlerAdded(ctx);
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel currentChannel = ctx.channel();
        System.out.println(currentChannel.remoteAddress()+" 离开\n");
        //验证一下每次客户端断开连接，连接自动地从channelGroup中删除调。
    }
    //连接处于活动状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() +" 上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() +" 下线了");
    }

}
