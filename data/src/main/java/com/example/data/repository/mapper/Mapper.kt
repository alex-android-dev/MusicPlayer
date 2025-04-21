package com.example.data.repository.mapper

import android.util.Log
import com.example.data.repository.model.TrackDto
import com.example.domain.entities.Track

internal class Mapper {

    fun mapTrackListDtoToTrackList(trackListDto: List<TrackDto>): List<Track> {
        val trackList: MutableList<Track> = mutableListOf()

        trackListDto.forEach { trackDto ->
            val track = trackDto.mapToTrack()

            trackList.add(track)
        }

        return trackList
    }

    fun mapTrackDtoToTrack(trackDto: TrackDto): Track {
        Log.d("Mapper", "track: $trackDto")
        return trackDto.mapToTrack()
    }

}

private fun TrackDto.mapToTrack(): Track = Track(
    compositionName = this.name,
    authorName = this.artist.name,
    coverUrl = this.album.cover,
    compositionUrl = this.compositionUrl,
    id = this.id,
    albumName = this.album.name,
    albumId = this.album.id,
)

