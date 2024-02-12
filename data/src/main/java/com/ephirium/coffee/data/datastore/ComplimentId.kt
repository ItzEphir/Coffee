package com.ephirium.coffee.data.datastore

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class ComplimentId : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var complimentId: String = ""
}