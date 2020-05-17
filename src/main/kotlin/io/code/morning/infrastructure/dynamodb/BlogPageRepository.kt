package io.code.morning.infrastructure.dynamodb

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@EnableScan
@EnableScanCount
@Repository
interface BlogPageRepository: PagingAndSortingRepository<BlogDto, String> {
}