package com.deyber.hackernews.domain.model.mappers

import com.deyber.hackernews.data.response.*
import com.deyber.hackernews.domain.model.ui.AfterFetchTimingModel
import com.deyber.hackernews.domain.model.ui.ExhaustiveModel
import com.deyber.hackernews.domain.model.ui.FetchTimingModel
import com.deyber.hackernews.domain.model.ui.HighlightFieldModel
import com.deyber.hackernews.domain.model.ui.HighlightResultModel
import com.deyber.hackernews.domain.model.ui.HitModel
import com.deyber.hackernews.domain.model.ui.NewsResponseModel
import com.deyber.hackernews.domain.model.ui.ProcessingTimingsMSModel
import com.deyber.hackernews.domain.model.ui.RequestTimingModel
import com.deyber.hackernews.domain.model.ui.TimingDetailModel

fun NewsResponse.toModel(): NewsResponseModel = NewsResponseModel(
    exhaustive = exhaustive?.toModel(),
    exhaustiveNbHits = exhaustiveNbHits,
    exhaustiveTypo = exhaustiveTypo,
    hits = hits?.map { it.toModel() },
    hitsPerPage = hitsPerPage,
    nbHits = nbHits,
    nbPages = nbPages,
    page = page,
    params = params,
    processingTimeMS = processingTimeMS,
    processingTimingsMS = processingTimingsMS?.toModel(),
    query = query,
    serverTimeMS = serverTimeMS
)

fun Exhaustive.toModel(): ExhaustiveModel = ExhaustiveModel(
    nbHits = nbHits,
    typo = typo
)

fun Hit.toModel(): HitModel = HitModel(
    highlightResult = highlightResult?.toModel(),
    tags = tags,
    author = author,
    commentText = commentText,
    createdAt = createdAt,
    createdAtI = createdAtI,
    objectID = objectID,
    parentId = parentId,
    storyId = storyId,
    storyTitle = storyTitle,
    storyUrl = storyUrl,
    updatedAt = updatedAt
)

fun HighlightResult.toModel(): HighlightResultModel = HighlightResultModel(
    author = author?.toModel(),
    commentText = commentText?.toModel(),
    storyTitle = storyTitle?.toModel(),
    storyUrl = storyUrl?.toModel()
)

fun HighlightField.toModel(): HighlightFieldModel = HighlightFieldModel(
    matchLevel = matchLevel,
    matchedWords = matchedWords,
    value = value
)

fun ProcessingTimingsMS.toModel(): ProcessingTimingsMSModel = ProcessingTimingsMSModel(
    request = request?.toModel(),
    afterFetch = afterFetch?.toModel(),
    fetch = fetch?.toModel(),
    total = total
)

fun RequestTiming.toModel(): RequestTimingModel = RequestTimingModel(
    roundTrip = roundTrip
)

fun AfterFetchTiming.toModel(): AfterFetchTimingModel = AfterFetchTimingModel(
    format = format?.toModel(),
    merge = merge?.toModel(),
    total = total
)

fun TimingDetail.toModel(): TimingDetailModel = TimingDetailModel(
    total = total
)

fun FetchTiming.toModel(): FetchTimingModel = FetchTimingModel(
    query = query,
    scanning = scanning,
    total = total
)
