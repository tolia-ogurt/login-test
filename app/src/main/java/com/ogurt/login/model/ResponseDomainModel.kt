package com.ogurt.login.model

data class ResponseDomainModel(
    val status: String,
    val user: User,
) {
    data class User(
        val api_key: String,
        val app_brand_id: Int,
        val avatar: Any,
        val blocked_data: BlockedData,
        val chat_id: String,
        val distributor_data: DistributorData,
        val firebase_id: String,
        val location_type: Any,
        val name: String,
        val payout_reject_msg: Any,
        val payout_status: Int,
        val phone_code: String,
        val phone_number: String,
        val place: Any,
        val referral: Any,
        val referral_bonus: Int,
        val second_name: String,
        val sector_type: SectorType,
        val solar_staff: Boolean,
        val type_blocked: Int,
        val userBalance: UserBalance,
        val user_id: Int,
        val user_type: Int,
    ) {
        data class BlockedData(
            val blocked_from: String,
            val blocked_items: BlockedItems,
            val blocked_to: Any,
            val comment: String,
            val percent_fine_bonuses: Int,
            val reason: String,
            val type_blocked: Int,
        ) {
            data class BlockedItems(
                val brands: List<Any>,
                val is_all_items: Boolean,
                val vendors: List<Any>,
            )
        }

        data class DistributorData(
            val brands: List<Any>,
            val distributor_status: Any,
            val distributor_vendor: Any,
            val moderator_msg: Any,
            val place_id: Any,
            val street_address: Any,
        )

        data class SectorType(
            val created_at: String,
            val id: Int,
            val image: Any,
            val is_active: Int,
            val title: String,
            val updated_at: String,
        )

        data class UserBalance(
            val balance: Int,
            val balance_on_moderation: Int,
            val balance_temp: Int,
            val sales_balance_accepted: Int,
            val sales_balance_moderated: Int,
            val sales_balance_send: Int,
            val total_payment_request: Int,
        )
    }
}