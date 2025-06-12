package cn.jbit.handset.impl;

import cn.jbit.handset.factory.Handset;
import cn.jbit.handset.factory.PlayWiring;

/**
 * 普通手机
 * @author 北大青鸟
 *
 */
public class CommonHandset extends Handset implements PlayWiring {
	
	public CommonHandset(){
		
	}
	
	public CommonHandset(String brand,String type){
		super(brand,type);
	}
	
	@Override
	public void play(String content) {
		System.out.println("开始播放音乐《"+content+"》......");
	}

	@Override
	public void sendInfo() {
		System.out.println("发送文字信息......");
	}

	@Override
	public void call() {
		System.out.println("开始语音通话.....");
	}
}
