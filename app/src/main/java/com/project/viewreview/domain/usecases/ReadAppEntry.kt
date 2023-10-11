package com.project.viewreview.domain.usecases

import com.project.viewreview.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManager
) {

    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}