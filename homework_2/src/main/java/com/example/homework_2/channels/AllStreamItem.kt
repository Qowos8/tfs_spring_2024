package com.example.homework_2.channels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllStreamItem(
    @SerialName("stream_id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("color")
    val color: String? = null,
    @SerialName("description")
    val description: String,
//    @SerialName("audible_notifications")
//    val audibleNotifications: Boolean? = null,
//    @SerialName("desktop_notifications")
//    val desktopNotifications: Boolean? = null,
//    @SerialName("invite_only")
//    val inviteOnly: Boolean,
//    @SerialName("is_muted")
//    val isMuted: Boolean,
//    @SerialName("pin_to_top")
//    val pinToTop: Boolean,
//    @SerialName("push_notifications")
//    val pushNotifications: Boolean? = null,
//    @SerialName("subscribers")
//    val subscribers: List<Int>? = null
)

@Serializable
data class SubStreamItem(
    @SerialName("stream_id")
    val streamId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
//    @SerialName("is_default")
//    val isDefault: Boolean,
//    @SerialName("invite_only")
//    val inviteOnly: Boolean,
//    @SerialName("is_announcement_only")
//    val isAnnouncementOnly: Boolean,
//    @SerialName("is_web_public")
//    val isWebPublic: Boolean,
//    @SerialName("history_public_to_subscribers")
//    val historyPublicToSubscribers: Boolean,
//    @SerialName("can_remove_subscribers_group")
//    val canRemoveSubscribersGroup: Int,
//    @SerialName("date_created")
//    val dateCreated: Long,
//    @SerialName("first_message_id")
//    val firstMessageId: Int,
//    @SerialName("stream_post_policy")
//    val streamPostPolicy: Int,
//    @SerialName("stream_weekly_traffic")
//    val streamWeeklyTraffic: Int? = null,
//    @SerialName("message_retention_days")
//    val messageRetentionDays: Int? = null,
//    @SerialName("rendered_description")
//    val renderedDescription: String? = null
)
