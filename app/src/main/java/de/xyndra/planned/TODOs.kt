package de.xyndra.planned

import kotlinx.serialization.Serializable

@Serializable
data class TODOList(
    val list: List<TODOInterface>
)

operator fun TODOList.get(index: Int): TODOInterface = list[index]
fun TODOList.size(): Int = list.size

fun emptyTODOList(): TODOList { return TODOList(emptyList()) }

@Serializable
class BasicTODO(
    override var title: String,
    override var state: TODOState
) : TODOInterface {
    override fun checkForStateChange() {
        TODO("Not yet implemented")
    }
}