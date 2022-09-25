package com.sufyan.foodrecipie.model


import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("results")
    val results: List<Result?>?
) {
    data class Result(
        @SerializedName("approved_at")
        val approvedAt: Int?,
        @SerializedName("aspect_ratio")
        val aspectRatio: String?,
        @SerializedName("beauty_url")
        val beautyUrl: String?,
        @SerializedName("brand")
        val brand: Brand?,
        @SerializedName("brand_id")
        val brandId: Int?,
        @SerializedName("buzz_id")
        val buzzId: Int?,
        @SerializedName("canonical_id")
        val canonicalId: String?,
        @SerializedName("compilations")
        val compilations: List<Compilation?>?,
        @SerializedName("cook_time_minutes")
        val cookTimeMinutes: Int?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("created_at")
        val createdAt: Int?,
        @SerializedName("credits")
        val credits: List<Credit?>?,
        @SerializedName("description")
        val description: String?,
        @SerializedName("draft_status")
        val draftStatus: String?,
        @SerializedName("facebook_posts")
        val facebookPosts: List<Any?>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("inspired_by_url")
        val inspiredByUrl: String?,
        @SerializedName("instructions")
        val instructions: List<Instruction?>?,
        @SerializedName("is_one_top")
        val isOneTop: Boolean?,
        @SerializedName("is_shoppable")
        val isShoppable: Boolean?,
        @SerializedName("keywords")
        val keywords: String?,
        @SerializedName("language")
        val language: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("num_servings")
        val numServings: Int?,
        @SerializedName("nutrition")
        val nutrition: Nutrition?,
        @SerializedName("nutrition_visibility")
        val nutritionVisibility: String?,
        @SerializedName("original_video_url")
        val originalVideoUrl: String?,
        @SerializedName("prep_time_minutes")
        val prepTimeMinutes: Int?,
        @SerializedName("promotion")
        val promotion: String?,
        @SerializedName("renditions")
        val renditions: List<Rendition?>?,
        @SerializedName("sections")
        val sections: List<Section?>?,
        @SerializedName("seo_title")
        val seoTitle: String?,
        @SerializedName("servings_noun_plural")
        val servingsNounPlural: String?,
        @SerializedName("servings_noun_singular")
        val servingsNounSingular: String?,
        @SerializedName("show")
        val show: Show?,
        @SerializedName("show_id")
        val showId: Int?,
        @SerializedName("similarity")
        val similarity: Double?,
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("tags")
        val tags: List<Tag?>?,
        @SerializedName("thumbnail_alt_text")
        val thumbnailAltText: String?,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?,
        @SerializedName("tips_and_ratings_enabled")
        val tipsAndRatingsEnabled: Boolean?,
        @SerializedName("topics")
        val topics: List<Topic?>?,
        @SerializedName("total_time_minutes")
        val totalTimeMinutes: Int?,
        @SerializedName("total_time_tier")
        val totalTimeTier: TotalTimeTier?,
        @SerializedName("updated_at")
        val updatedAt: Int?,
        @SerializedName("user_ratings")
        val userRatings: UserRatings?,
        @SerializedName("video_ad_content")
        val videoAdContent: String?,
        @SerializedName("video_id")
        val videoId: Int?,
        @SerializedName("video_url")
        val videoUrl: String?,
        @SerializedName("yields")
        val yields: String?
    ) {
        data class Brand(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("image_url")
            val imageUrl: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?
        )

        data class Compilation(
            @SerializedName("approved_at")
            val approvedAt: Int?,
            @SerializedName("aspect_ratio")
            val aspectRatio: String?,
            @SerializedName("beauty_url")
            val beautyUrl: String?,
            @SerializedName("buzz_id")
            val buzzId: Any?,
            @SerializedName("canonical_id")
            val canonicalId: String?,
            @SerializedName("country")
            val country: String?,
            @SerializedName("created_at")
            val createdAt: Int?,
            @SerializedName("description")
            val description: String?,
            @SerializedName("draft_status")
            val draftStatus: String?,
            @SerializedName("facebook_posts")
            val facebookPosts: List<Any?>?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("is_shoppable")
            val isShoppable: Boolean?,
            @SerializedName("keywords")
            val keywords: Any?,
            @SerializedName("language")
            val language: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("promotion")
            val promotion: String?,
            @SerializedName("show")
            val show: List<Show?>?,
            @SerializedName("slug")
            val slug: String?,
            @SerializedName("thumbnail_alt_text")
            val thumbnailAltText: String?,
            @SerializedName("thumbnail_url")
            val thumbnailUrl: String?,
            @SerializedName("video_id")
            val videoId: Int?,
            @SerializedName("video_url")
            val videoUrl: String?
        ) {
            data class Show(
                @SerializedName("id")
                val id: Int?,
                @SerializedName("name")
                val name: String?
            )
        }

        data class Credit(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("image_url")
            val imageUrl: String?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Instruction(
            @SerializedName("appliance")
            val appliance: String?,
            @SerializedName("display_text")
            val displayText: String?,
            @SerializedName("end_time")
            val endTime: Int?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("position")
            val position: Int?,
            @SerializedName("start_time")
            val startTime: Int?,
            @SerializedName("temperature")
            val temperature: Int?
        )

        data class Nutrition(
            @SerializedName("calories")
            val calories: Int?,
            @SerializedName("carbohydrates")
            val carbohydrates: Int?,
            @SerializedName("fat")
            val fat: Int?,
            @SerializedName("fiber")
            val fiber: Int?,
            @SerializedName("protein")
            val protein: Int?,
            @SerializedName("sugar")
            val sugar: Int?,
            @SerializedName("updated_at")
            val updatedAt: String?
        )

        data class Rendition(
            @SerializedName("aspect")
            val aspect: String?,
            @SerializedName("bit_rate")
            val bitRate: Int?,
            @SerializedName("container")
            val container: String?,
            @SerializedName("content_type")
            val contentType: String?,
            @SerializedName("duration")
            val duration: Int?,
            @SerializedName("file_size")
            val fileSize: Int?,
            @SerializedName("height")
            val height: Int?,
            @SerializedName("maximum_bit_rate")
            val maximumBitRate: Int?,
            @SerializedName("minimum_bit_rate")
            val minimumBitRate: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("poster_url")
            val posterUrl: String?,
            @SerializedName("url")
            val url: String?,
            @SerializedName("width")
            val width: Int?
        )

        data class Section(
            @SerializedName("components")
            val components: List<Component?>?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("position")
            val position: Int?
        ) {
            data class Component(
                @SerializedName("extra_comment")
                val extraComment: String?,
                @SerializedName("id")
                val id: Int?,
                @SerializedName("ingredient")
                val ingredient: Ingredient?,
                @SerializedName("measurements")
                val measurements: List<Measurement?>?,
                @SerializedName("position")
                val position: Int?,
                @SerializedName("raw_text")
                val rawText: String?
            ) {
                data class Ingredient(
                    @SerializedName("created_at")
                    val createdAt: Int?,
                    @SerializedName("display_plural")
                    val displayPlural: String?,
                    @SerializedName("display_singular")
                    val displaySingular: String?,
                    @SerializedName("id")
                    val id: Int?,
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("updated_at")
                    val updatedAt: Int?
                )

                data class Measurement(
                    @SerializedName("id")
                    val id: Int?,
                    @SerializedName("quantity")
                    val quantity: String?,
                    @SerializedName("unit")
                    val unit: Unit?
                ) {
                    data class Unit(
                        @SerializedName("abbreviation")
                        val abbreviation: String?,
                        @SerializedName("display_plural")
                        val displayPlural: String?,
                        @SerializedName("display_singular")
                        val displaySingular: String?,
                        @SerializedName("name")
                        val name: String?,
                        @SerializedName("system")
                        val system: String?
                    )
                }
            }
        }

        data class Show(
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?
        )

        data class Tag(
            @SerializedName("display_name")
            val displayName: String?,
            @SerializedName("id")
            val id: Int?,
            @SerializedName("name")
            val name: String?,
            @SerializedName("type")
            val type: String?
        )

        data class Topic(
            @SerializedName("name")
            val name: String?,
            @SerializedName("slug")
            val slug: String?
        )

        data class TotalTimeTier(
            @SerializedName("display_tier")
            val displayTier: String?,
            @SerializedName("tier")
            val tier: String?
        )

        data class UserRatings(
            @SerializedName("count_negative")
            val countNegative: Int?,
            @SerializedName("count_positive")
            val countPositive: Int?,
            @SerializedName("score")
            val score: Double?
        )
    }
}