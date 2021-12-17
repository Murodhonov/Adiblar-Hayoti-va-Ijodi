package uz.umarxon.adiblar.models

import java.io.Serializable

class AdibClass:Serializable{
    var name:String? = null
    var birth:String? = null
    var die:String? = null
    var type:String? = null
    var about:String? = null
    var imageUrl:String? = null
    var isLiked = false

    constructor(name: String?, birth: String?, die: String?, type: String?, about: String?, imageUrl: String?) {
        this.name = name
        this.birth = birth
        this.die = die
        this.type = type
        this.about = about
        this.imageUrl = imageUrl
    }

    constructor()

    override fun toString(): String {
        return "AdibClass(name=$name, birth=$birth, die=$die, type=$type, about=$about, imageUrl=$imageUrl)"
    }


}
