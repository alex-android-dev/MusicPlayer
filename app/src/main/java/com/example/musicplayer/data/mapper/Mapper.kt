package com.example.musicplayer.data.mapper

import com.example.musicplayer.data.model.ChartContentDto
import com.example.musicplayer.domain.Entities.Track

class Mapper {

    fun mapChartContentToTrackList(chartContent: ChartContentDto): List<Track> {

        val trackList: MutableList<Track> = mutableListOf()

        chartContent.tracksContentDto.trackListDto.forEach { trackDto ->

            val track = Track(
                name = trackDto.name,
                author = trackDto.artist.name,
                coverUrl = trackDto.album.cover,
                compositionUrl = trackDto.compositionUrl,
                id = trackDto.id,
            )

            trackList.add(track)
        }

        return trackList.toList()
    }


}