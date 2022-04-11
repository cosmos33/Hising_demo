package com.mm.hisingdemo.clienthandler

import android.util.Log
import com.mm.hising.client.IHisingRtcHandler
import com.mm.hising.client.bean.Song

class HisingRtcHandler: IHisingRtcHandler {
    override fun prepareLoadSong(song: Song, callBack: IHisingRtcHandler.LoadSongCallBack?) {
        callBack?.onLoadSong(song)
    }

    override fun playSong(song: Song, playSongCallBack: IHisingRtcHandler.PlaySongCallBack) {
        Log.v("SSSS","正在播放歌手${song.singer}的${song.name}")
    }

    override fun stop() {
        Log.v("SSSS","歌曲stop")
    }

    override fun release() {
        Log.v("SSSS","player release")
    }
}