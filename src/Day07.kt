fun main() {

    fun toFilesystemMap(
        input: List<String>
    ): MutableMap<String, File> {
        var currentDir = ""
        val fileSystem = mutableMapOf<String, File>()
        var totalSpace = 0L
        input.forEach { line ->
            when {
                line.startsWith("$ cd ") -> {
                    val askedDir = line.substringAfterLast(" ")
                    currentDir = when (askedDir) {
                        ".." -> currentDir.substringBeforeLast("/")
                        "/" -> askedDir
                        else -> currentDir.plus(
                            (if (currentDir != "/") "/" else "") + askedDir
                        )
                    }
                    if (currentDir.isEmpty())
                        currentDir = "/"
                }

                line.startsWith("$ ls") -> {}//rien
                line.startsWith("dir ") -> {
                    val directory = line.substringAfterLast(" ")
                    val path = when (currentDir) {
                        "/" -> "$currentDir$directory"
                        else -> "$currentDir/$directory"
                    }
                    fileSystem.putIfAbsent(
                        path,
                        File(
                            name = directory,
                            isDirectory = true,
                            level = path.count { it == '/' },
                            path = path
                        )
                    )
                }

                else -> { // files
                    line.split(" ").let { (size, name) ->
                        val path = when (currentDir) {
                            "/" -> "$currentDir$name"
                            else -> "$currentDir/$name"
                        }
                        fileSystem.putIfAbsent(
                            path,
                            File(
                                size = size.toLong(),
                                name = name,
                                isDirectory = false,
                                level = path.count { it == '/' },
                                path = path
                            )
                        )
                        totalSpace += size.toLong()
                        var parents = currentDir
                        while (parents.isNotEmpty()) {
                            if (fileSystem.containsKey(parents))
                                fileSystem[parents]!!.addSize(size.toLong())
                            parents = parents.substringBeforeLast("/")
                        }
                    }
                }
            }
        }
        fileSystem["/"] = File(size = totalSpace, isDirectory = true, level = 1, path = "/")
        return fileSystem
    }

    fun part1(input: List<String>): Long =
        toFilesystemMap(input).filter {
            it.value.isDirectory && it.value.size <= 100000L
        }.toList().sumOf {
            it.second.size
        }


    fun part2(input: List<String>): Long {
        val fileSystem = toFilesystemMap(input)
        val unUsedSpace = TOTAL_FILESYTEM_DISK_SPACE - fileSystem["/"]!!.size

        return fileSystem.values.filter { file ->
            val unUsedSpaceIfFileRemove = unUsedSpace + file.size
            file.isDirectory
                    && unUsedSpaceIfFileRemove >= MIN_UNUSED_SPACE
        }.minOf {
            it.size
        }
    }

    val testInput = readInput("Day${DAY_NUMBER}_test")
    val resultPart1 = part1(testInput)
    check(resultPart1 == 95437L)
    val resultPart2 = part2(testInput)
    check(resultPart2 == 24933642L)

    val input = readInput("Day$DAY_NUMBER")
    println(part1(input))
    println(part2(input))
}

private const val DAY_NUMBER = "07"

data class File(
    var size: Long = 0L,
    val name: String = "",
    val isDirectory: Boolean,
    val level: Int,
    val path: String
) {
    fun addSize(sizeToAdd: Long) {
        size += sizeToAdd
    }

}


const val TOTAL_FILESYTEM_DISK_SPACE = 70000000L
const val MIN_UNUSED_SPACE = 30000000L