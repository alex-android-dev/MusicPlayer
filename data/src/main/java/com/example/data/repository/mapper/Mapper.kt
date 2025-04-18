package com.example.data.repository.mapper

import com.example.data.repository.model.ChartContentDto
import com.example.domain.entities.Track

internal class Mapper {

    fun mapChartContentToTrackList(chartContent: ChartContentDto): List<Track> {

        val trackList: MutableList<Track> = mutableListOf()

        chartContent.tracksContentDto.trackListDto.forEach { trackDto ->

            val track = Track(
                compositionName = trackDto.name,
                authorName = trackDto.artist.name,
                coverUrl = trackDto.album.cover,
                compositionUrl = trackDto.compositionUrl,
                id = trackDto.id,
            )

            trackList.add(track)
        }

        return trackList.toList()
    }


}