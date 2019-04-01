package com.fanwe.g419.event;

import com.fanwe.g419.model.LiveSongModel;

public class EPlayMusic
{
	public LiveSongModel songModel;
	public EPlayMusic(LiveSongModel model) {
		songModel = model;
	}
	public EPlayMusic() {
		songModel = null;
	}
}
