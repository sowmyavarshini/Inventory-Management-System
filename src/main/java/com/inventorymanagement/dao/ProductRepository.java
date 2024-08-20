package com.inventorymanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entity.Product;

/**
 * Repository interface for accessing Product entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	/**
	 * Finds products with IDs greater than the specified value.
	 *
	 * @param productId the product ID threshold
	 * @return a list of products with IDs greater than the specified value
	 */
	@Query("select p from Product p where p.productId> :productId1")
	List<Product> findProductById(@Param("productId1") int productId);

	/**
	 * Finds products with names matching the specified pattern.
	 *
	 * @param productName the product name pattern
	 * @return a list of products with names matching the specified pattern
	 */
	@Query("select p from Product p where p.productName LIKE %:name1%")
	List<Product> findByName(@Param("name1") String productName);

//	Fetches only few columns from Product
	/**
	 * Fetches only the product name and price for products with prices greater than
	 * the specified value.
	 *
	 * @param price the price threshold
	 * @return a list of customized product data
	 */
	@Query("select p.productName as productName, p.price as price from Product p where p.price> :price1")
	List<ProductCustomized> findProductByPriceCustomized(@Param("price1") float price);

//	Named query
	/**
	 * Finds all products ordered by name in descending order using a named query.
	 *
	 * @return a list of products ordered by name descending
	 */
	List<Product> findAllProductsByNameDescending();

//	Inner join
	/**
	 * Finds products and their associated order details using an inner join.
	 *
	 * @return a list of products and order details
	 */
	@Query("select p from Product p JOIN  OrderDetails o on p.productId = o.product.productId")
	List<Product> findProductsAndOrderDetails();

//	Left Outer Join
	/**
	 * Finds products with their associated order details using a left outer join.
	 *
	 * @return a list of products with their order details
	 */
	@Query("select p from Product p LEFT OUTER JOIN OrderDetails o on p.productId=o.product.productId")
	List<Product> findProductWithOrdersLeft();

//	Customized data by join
	/**
	 * Finds customized product and order data by joining product and order details.
	 *
	 * @param price the price threshold
	 * @return a list of customized product and order data
	 */
	@Query("select p.productName as productName , p.price as price, o.orderedDate as orderedDate , o.deliveryDate as deliveryDate from Product p JOIN OrderDetails o on p.productId=o.product.productId where p.price> :price1")
	List<ProductOrderCustomized> findByProductOrderCustomized(@Param("price1") float price);

//	Aggregate function
	/**
	 * Finds the count of products grouped by category.
	 *
	 * @return a list of category names and product counts
	 */
	@Query("select c.categoryName as categoryName, COUNT(p.productId) as productCount FROM Product p JOIN Category c ON p.category.categoryId = c.categoryId GROUP BY c.categoryName HAVING COUNT(p.productId) > 1")
	List<ProductCategoryCustomized> findProductsCategoryCount();

//	Math function
	/**
	 * Finds the average price of products grouped by category.
	 *
	 * @return a list of category names and average prices
	 */
	@Query("select c.categoryName as categoryName, AVG(p.price) as averagePrice FROM Product p JOIN p.category c GROUP BY c.categoryName")
	List<CategoryPriceCustomized> findAvergePriceByCategory();

//	String function 
	/**
	 * Finds products with names converted to uppercase.
	 *
	 * @return a list of products with names in uppercase
	 */
	@Query("select p.productId as productId, UPPER(p.productName) as productName, p.price as price FROM Product p")
	List<ProductUpper> findProductNamesUppercase();

//	Clause
	/**
	 * Finds the count of products grouped by brand, filtered by brand name and
	 * product count.
	 *
	 * @param brandName    the brand name pattern
	 * @param productCount the minimum product count
	 * @return a list of brand names and product counts
	 */
	@Query("select b.brandName as brandName, COUNT(p.productId) as productCount FROM Product p JOIN p.brand b "
			+ "WHERE b.brandName LIKE %:name% GROUP BY b.brandName HAVING COUNT(p.productId) > :number ORDER BY b.brandName ASC")
	List<ProductBrandCustomized> findProductCountByBrand(@Param("name") String brandName,
			@Param("number") int productCount);

//	Joins
	/**
	 * Finds product and order details by joining product, order details, brand, and
	 * category.
	 *
	 * @return a list of customized product, order, brand, and category data
	 */
	@Query("SELECT p.productId as productId, p.productName as productName, "
			+ "o.orderId as orderId, o.orderedQuantity as orderedQuantity, "
			+ "b.brandName as brandName, c.categoryName as categoryName " + "FROM Product p " + "JOIN p.orderDetails o "
			+ "JOIN p.brand b " + "JOIN p.category c")
	List<ProductOrderCBCustomized> findProductOrderDetails();

	@Query("SELECT p.productId AS productId, p.productName AS productName, b.brandName AS brand, c.categoryName AS category"
			+ ",p.stockAvailable AS stockAvailable, p.price AS price,"
			+ "co.customer.customerId AS customerId, co.customer.username AS username, od.orderId AS orderId,"
			+ " od.orderedQuantity AS orderedQuantity, "
			+ "(od.orderedQuantity * p.price) AS amountPaid FROM OrderDetails od "
			+ "INNER JOIN Product p ON od.product.productId = p.productId "
			+ "INNER JOIN Brand b ON p.brand.brandId = b.brandId "
			+ "INNER JOIN Category c ON p.category.categoryId = c.categoryId "
			+ "INNER JOIN CustomerOrderDetails co ON od.orderId = co.order.orderId "
			+ "ORDER BY p.productId, co.customer.customerId, od.orderId")
	List<SalesReportCustomized> generateSalesReport();

	@Query("SELECT c.customerId AS customerId,c.username AS userName,p.productId as productId,p.productName as productName,"
			+ " p.price as price, od.orderedQuantity as orderedQuantity , (od.orderedQuantity * p.price) AS amountPaid "
			+ "FROM CustomerOrderDetails co INNER JOIN Customer c ON co.customer.customerId = c.customerId "
			+ "INNER JOIN OrderDetails od ON co.order.orderId = od.orderId "
			+ "INNER JOIN Product p ON od.product.productId = p.productId where c.customerId= :customerId1")
	List<CustomerOrderReportCustomized> generateCustomerOrderReport(@Param("customerId1") int customerId);

	@Query("SELECT p FROM Product p JOIN p.brand b WHERE b.brandName = :brandName1")
	List<Product> findProductsByBrandName(@Param("brandName1") String brandName1);

	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.categoryName = :categoryName1")
	List<Product> findProductsByCategoryName(@Param("categoryName1") String categoryName);

	boolean existsByProductName(String productName);

	boolean existsByBarcode(String barcode);

}
