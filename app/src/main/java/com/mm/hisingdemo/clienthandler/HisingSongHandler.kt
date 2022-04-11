package com.mm.hisingdemo.clienthandler

import com.mm.hising.client.IHisingSongHandler
import com.mm.hising.client.bean.Song

class HisingSongHandler : IHisingSongHandler {
    val icon = "https://alifei05.cfp.cn/creative/vcg/800/new/VCG41N1224883700.jpg"
    override fun getSongList(rankList: Int, pageSize: Int, pageIndex: Int, callback: IHisingSongHandler.SongCallBack) {
        val result = ArrayList<Song>(pageSize)
        for (index in 0..pageSize) {
            val song = Song()
            song.coverUrl = icon
            song.name = "${rankList}_ ${pageSize * pageIndex + index}-歌的名字"
            song.singer = "${pageSize * pageIndex + index}+未知歌手"
            song.musicId = (pageSize * pageIndex + index).toString()
            result.add(song)
        }
        callback.onGetSongList(result)
    }

    override fun searchSongList(content: String, pageSize: Int, pageIndex: Int, callback: IHisingSongHandler.SearchCallBack) {
        val result = ArrayList<Song>(pageSize)
        for (index in 0..pageSize) {
            val song = Song()
            song.coverUrl = icon
            song.name = "搜索到的-歌的名字"
            song.singer = "${pageSize * pageIndex + index}+未知歌手"
            song.musicId = (pageSize * pageIndex + index).toString()
            result.add(song)
        }
        callback.onSearchSongList(result)
    }
}