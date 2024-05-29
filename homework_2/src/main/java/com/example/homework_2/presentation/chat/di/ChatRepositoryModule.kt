package com.example.homework_2.presentation.chat.di

import com.example.homework_2.data.repository_impl.ChatRepositoryImpl
import com.example.homework_2.domain.repository.ChatRepository
import com.example.homework_2.domain.use_case.chat.DeleteReactionUseCase
import com.example.homework_2.domain.use_case.chat.GetMessagesUseCase
import com.example.homework_2.domain.use_case.chat.GetNextMessagesUseCase
import com.example.homework_2.domain.use_case.chat.RegisterEventUseCase
import com.example.homework_2.domain.use_case.chat.SendMessageUseCase
import com.example.homework_2.domain.use_case.chat.SendReactionUseCase
import com.example.homework_2.domain.use_case.chat.TrackEventUseCase
import com.example.homework_2.domain.use_case.chat.UpdateMessagesUseCase
import com.example.homework_2.domain.use_case.chat.impl.DeleteReactionUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.GetMessagesUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.GetNextMessagesUseCaseUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.RegisterEventUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.SendMessageUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.SendReactionUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.TrackEventUseCaseImpl
import com.example.homework_2.domain.use_case.chat.impl.UpdateMessagesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
internal interface ChatRepositoryModule {

    @Binds
    fun provideChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository

    @Binds
    fun provideDeleteReactionUseCase(deleteReactionUseCaseImpl: DeleteReactionUseCaseImpl): DeleteReactionUseCase

    @Binds
    fun provideGetMessagesUseCase(getMessagesUseCaseImpl: GetMessagesUseCaseImpl): GetMessagesUseCase

    @Binds
    fun provideRegisterEventUseCase(registerEventUseCaseImpl: RegisterEventUseCaseImpl): RegisterEventUseCase

    @Binds
    fun provideSendMessageUseCase(sendMessageUseCaseImpl: SendMessageUseCaseImpl): SendMessageUseCase

    @Binds
    fun provideSendReactionUseCase(sendReactionUseCaseImpl: SendReactionUseCaseImpl): SendReactionUseCase

    @Binds
    fun provideTrackEventUseCase(trackEventUseCaseImpl: TrackEventUseCaseImpl): TrackEventUseCase

    @Binds
    fun provideGetNextMessageUseCase(getNextMessagesUseCaseImpl: GetNextMessagesUseCaseUseCaseImpl): GetNextMessagesUseCase

    @Binds
    fun provideUpdateMessageUseCase(updateMessagesUseCaseImpl: UpdateMessagesUseCaseImpl): UpdateMessagesUseCase
}