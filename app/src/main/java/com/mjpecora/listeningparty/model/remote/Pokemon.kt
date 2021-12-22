/*
* Lowe's Companies Inc., Android Application
* Copyright (C)  Lowe's Companies Inc.
*
*  The Lowe's Application is the private property of
*  Lowe's Companies Inc. Any distribution of this software
*  is unlawful and prohibited.
*/
package com.mjpecora.listeningparty.model.remote

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Pokemon(
    var page: Int = 0,
    @PrimaryKey val name: String = "",
    val url: String = "",
) {
    val officialArtworkUrl: String
        get() {
            val index = url.split('/').dropLast(1).last()
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/" +
                    "pokemon/other/official-artwork/$index.png"
        }
}

@Serializable data class PokemonResponse(@SerialName("results") val list: List<Pokemon>)

@Entity
@Serializable
data class PokemonDetail(
    val id: Int,
    val name: String,
    @SerialName("base_experience") val baseExperience: Int,
    val height: Int,
    @SerialName("is_default") val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val forms: List<Form>,
    @SerialName("game_indices") val gameIndices: List<GameIndex>,
    @SerialName("held_items") val heldItems: List<HeldItem>,
    val sprites: Sprites
) {

    override fun toString(): String {
        return "$name: weight:$weight"
    }

}

@Serializable data class OtherArtwork(
    @SerialName("official-artwork") val officialArtwork: OfficialArtwork
)

@Serializable data class OfficialArtwork(
    @SerialName("front_default") val frontDefaultUrl: String
)

@Serializable data class Sprites(
    @SerialName("front_default") val frontDefaultUrl: String,
    @SerialName("other") val otherArtwork: OtherArtwork
)


@Serializable data class Form(
    val name: String,
    val url: String
)

@Serializable data class GameIndex(
    @SerialName("game_index") val gameIndex: Int,
    val version: Version
)

@Serializable data class HeldItem(
    val item: Item,
    @SerialName("version_details") val versionDetails: List<VersionDetails>
)

@Serializable data class Item(
    val name: String,
    val url: String
)

@Serializable data class VersionDetails(
    val rarity: Int,
    val version: Version
)

@Serializable data class Version(
    val name: String,
    val url: String
)

@Serializable data class Ability(
    @SerialName("is_hidden") val isHidden: Boolean = false,
)