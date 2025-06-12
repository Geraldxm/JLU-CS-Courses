package cn.jbit.handset.impl;

import cn.jbit.handset.factory.Handset;
import cn.jbit.handset.factory.Network;
import cn.jbit.handset.factory.PlayWiring;
import cn.jbit.handset.factory.TheakePictures;

/**
 * 智能手机
 * @author 北大青鸟
 *
 */
public class AptitudeHandset extends Handset implements Network, PlayWiring,
		TheakePictures {
	
	public AptitudeHandset(){
		
	}
	
	public AptitudeHandset(String brand,String type){
		super(brand,type);
	}

	@Override
	public void sendInfo() {
		System.out.println("发送带图片与文字的信息......");	
	}

	@Override
	public void takePicture() {
		System.out.println("咔嚓......拍照成功");
	}

	@Override
	public void play(String content) {
		System.out.println("开始播放视频《"+content+"》......");
	}

	@Override
	public void networkConn() {
		System.out.println("已经启动移动网络......");
	}

	@Override
	public void call() {
		System.out.println("开始视频通话......");

	}

}
