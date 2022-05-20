package com.example.mycryptoc.data

data class CryptoData(val data: List<DataItem>?,
                      val timestamp: Long = 0)


data class DataItem(val symbol: String = "",
                    val volumeUsdHr: String = "",
                    val marketCapUsd: String = "",
                    val priceUsd: String = "",
                    val vwapHr: String = "",
                    val changePercent24Hr: String = "",
                    val name: String = "",
                    val explorer: String = "",
                    val rank: String = "",
                    val id: String = "",
                    val maxSupply: String = "",
                    val supply: String = "")


