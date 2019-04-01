package com.fanwe.g419.event;

import com.fanwe.g419.model.LiveSongModel;


public class ELiveSongDownload {
	public LiveSongModel songModel;

	
	public ELiveSongDownload() {
		songModel = null;
	}
	
	public  ELiveSongDownload(LiveSongModel songModel) {
		this.songModel = songModel;
		
	}
}
