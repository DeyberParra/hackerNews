package com.deyber.database.repository

import com.deyber.database.daos.HitDao
import com.deyber.database.data.DBResource
import com.deyber.database.data.HitWithHighlightResult
import com.deyber.database.di.HitDaoQualifier
import com.deyber.database.entities.HighlightResultEntity
import com.deyber.database.entities.HitEntity
import javax.inject.Inject

class HitRepositoryImp @Inject constructor(
    @HitDaoQualifier
    private val hitDao: HitDao
): HitRepository{

    override suspend fun addHits(hits:List<Pair<HitEntity, HighlightResultEntity?>>): DBResource<Boolean> {
        return try {
            hitDao.insertHitsWithHighlightResults(hits)
            DBResource.Success(true)
        } catch (e : Throwable){
            DBResource.Error(e)
        }
    }

    override suspend fun getHits(): DBResource<List<HitWithHighlightResult>> {
        return try {
            val hits = hitDao.getHitsWithHighlightResults()
            DBResource.Success(hits)
        } catch (e :Throwable){
            DBResource.Error(e)
        }
    }

    override suspend fun deleteHits(id: Long, createAt: Long): DBResource<Boolean> {
        return try {
            val hitId = hitDao.getHitId(id, createAt)
            hitDao.deleteHit(hitId)
            DBResource.Success(true)
        }catch (e :Throwable){
            DBResource.Error(e)
        }
    }
}