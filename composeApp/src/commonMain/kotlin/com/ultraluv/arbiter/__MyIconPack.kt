package com.ultraluv.arbiter

import androidx.compose.ui.graphics.vector.ImageVector
import com.ultraluv.arbiter.myiconpack.AdsClick24dp
import com.ultraluv.arbiter.myiconpack.ArrowBack24dp
import com.ultraluv.arbiter.myiconpack.Bomb24dp
import com.ultraluv.arbiter.myiconpack.Circle24dp
import com.ultraluv.arbiter.myiconpack.Counter124dp
import com.ultraluv.arbiter.myiconpack.Counter224dp
import com.ultraluv.arbiter.myiconpack.Counter324dp
import com.ultraluv.arbiter.myiconpack.Counter424dp
import com.ultraluv.arbiter.myiconpack.Counter524dp
import com.ultraluv.arbiter.myiconpack.Counter624dp
import com.ultraluv.arbiter.myiconpack.Counter724dp
import com.ultraluv.arbiter.myiconpack.Counter824dp
import com.ultraluv.arbiter.myiconpack.Flag24dp
import com.ultraluv.arbiter.myiconpack.PlayArrow24dp
import com.ultraluv.arbiter.myiconpack.PlayCircle24dp
import com.ultraluv.arbiter.myiconpack.Replay24dp
import com.ultraluv.arbiter.myiconpack.Settings24dp
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(AdsClick24dp, ArrowBack24dp, Bomb24dp, Circle24dp, Counter124dp,
        Counter224dp, Counter324dp, Counter424dp, Counter524dp, Counter624dp, Counter724dp,
        Counter824dp, Flag24dp, PlayArrow24dp, PlayCircle24dp, Replay24dp, Settings24dp)
    return __AllIcons!!
  }
