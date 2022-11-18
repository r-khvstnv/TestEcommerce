package com.rkhvstnv.testecommerce.core_data.data.utils

import com.rkhvstnv.testecommerce.core_data.data.model.*
import com.rkhvstnv.testecommerce.core_data.data.model.BestSeller
import com.rkhvstnv.testecommerce.core_data.data.model.HomeStore
import com.rkhvstnv.testecommerce.core_data.data.model.PhoneDto
import com.rkhvstnv.testecommerce.core_data.data.model.Pojo
import com.rkhvstnv.testecommerce.core_data.domain.MyResult
import com.rkhvstnv.testecommerce.core_data.domain.model.BestSellerProduct
import com.rkhvstnv.testecommerce.core_data.domain.model.Category
import com.rkhvstnv.testecommerce.core_data.domain.model.HotSale
import com.rkhvstnv.testecommerce.core_data.domain.model.Phone
import com.rkhvstnv.testecommerce.utils.Mapper

internal class BestSellerToBestSellerProductMapper: Mapper<BestSeller, BestSellerProduct>{
    override fun map(input: BestSeller): BestSellerProduct = with(input){
        BestSellerProduct(
            id = id,
            is_favorites = is_favorites,
            image = picture,
            title = title,
            price_without_discount = price_without_discount,
            discount_price = discount_price,
        )
    }
}

internal class HomeStoreToHotSaleMapper: Mapper<HomeStore, HotSale>{
    override fun map(input: HomeStore): HotSale = with(input){
        HotSale(
            id = id,
            is_buy = is_buy,
            is_new = is_new,
            image = picture,
            title = title,
            subtitle = subtitle,
        )
    }
}

internal class PojoNetworkResultToHotSaleListResultMapper: Mapper<MyResult<Pojo>, MyResult<List<HotSale>>>{
    override fun map(input: MyResult<Pojo>): MyResult<List<HotSale>>{
        return when(input){
            is MyResult.Error -> MyResult.Error(code = input.code, message = input.message)
            is MyResult.Exception -> MyResult.Exception(e = input.e)
            is MyResult.Success -> {
                val hotSales = input.data.home_store.map {
                        homeStore: HomeStore ->
                    HotSale(
                        id = homeStore.id,
                        is_buy = homeStore.is_buy,
                        is_new = homeStore.is_new,
                        image = homeStore.picture,
                        title = homeStore.title,
                        subtitle = homeStore.subtitle
                    )
                }
                MyResult.Success(data = hotSales)

            }
        }
    }
}

internal class PojoNetworkResultToBestSellerProductListResultMapper: Mapper<MyResult<Pojo>, MyResult<List<BestSellerProduct>>>{
    override fun map(input: MyResult<Pojo>): MyResult<List<BestSellerProduct>>{
        return when(input){
            is MyResult.Error -> MyResult.Error(code = input.code, message = input.message)
            is MyResult.Exception -> MyResult.Exception(e = input.e)
            is MyResult.Success -> {
                val bestSellers = input.data.best_seller.map {
                    bestSeller: BestSeller ->
                    BestSellerProduct(
                        id = bestSeller.id,
                        is_favorites = bestSeller.is_favorites,
                        image = bestSeller.picture,
                        title = bestSeller.title,
                        price_without_discount = bestSeller.price_without_discount,
                        discount_price = bestSeller.discount_price
                    )
                }
                MyResult.Success(data = bestSellers)
            }
        }
    }
}

internal class PhoneDtoResultToPhoneResultMapper: Mapper<MyResult<PhoneDto>, MyResult<Phone>>{
    override fun map(input: MyResult<PhoneDto>): MyResult<Phone> {
        return when(input){
            is MyResult.Error -> MyResult.Error(code = input.code, message = input.message)
            is MyResult.Exception -> MyResult.Exception(e = input.e)
            is MyResult.Success -> {
                with(input.data){
                    val phone = Phone(
                        id = id,
                        isFavorites = isFavorites,
                        title = title,
                        images = images,
                        cpu = CPU,
                        camera = camera,
                        ram = ssd,
                        sd = sd,
                        capacity = capacity,
                        color = color,
                        price = price,
                        rating = rating
                    )

                    MyResult.Success(data = phone)
                }
            }
        }
    }

}

internal class CategoryDtoToCategoryMapper: Mapper<CategoryDto, Category>{
    override fun map(input: CategoryDto): Category = with(input){
        Category(
            id = id,
            name = name,
            image = image,
            isSelected = false
        )
    }

}