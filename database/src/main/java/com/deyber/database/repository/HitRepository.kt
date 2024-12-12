package com.deyber.database.repository

import com.deyber.database.data.DBResource
import com.deyber.database.data.HitWithHighlightResult
import com.deyber.database.entities.HighlightResultEntity
import com.deyber.database.entities.HitEntity

interface HitRepository {

    suspend fun addHits(hits:List<Pair<HitEntity, HighlightResultEntity?>>) : DBResource<Boolean>
    suspend fun getHits(): DBResource<List<HitWithHighlightResult>>
    suspend fun deleteHits(id: Long, createAt:Long): DBResource<Boolean>

}