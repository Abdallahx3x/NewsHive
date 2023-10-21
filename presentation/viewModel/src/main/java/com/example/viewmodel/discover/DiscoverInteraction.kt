package com.example.viewmodel.discover


interface DiscoverInteraction {
   fun  onClickCategoryItem(categoryNewsUiState: CategoryNewsUiState)
   fun updatePageIndex(pageIndex:Int)
}