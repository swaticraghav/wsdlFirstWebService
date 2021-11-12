package com.webservice.soap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.feature.Features;

import com.bharath.ws.trainings.CreateOrdersRequest;
import com.bharath.ws.trainings.CreateOrdersResponse;
import com.bharath.ws.trainings.CustomerOrdersPortType;
import com.bharath.ws.trainings.GetOrdersRequest;
import com.bharath.ws.trainings.GetOrdersResponse;
import com.bharath.ws.trainings.Order;
import com.bharath.ws.trainings.Product;

//@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class CustomerOrdersWsImpl implements CustomerOrdersPortType {

	// create a HASH MAP which will act like DB.
	// key: incoming customerID and response will be the List<Order>
	Map<BigInteger, List<Order>> map = new HashMap<>();

	// Map has key and value.
	// key is the id and we will use a variable for this.
	// Value is the List<Order> and we will create the init() method and put the
	// code inside the init() method for value.
	private int customerID;

	// we will put the code to use the DB Map in init() method.
	// we will call the init() method in the constructor.
	public CustomerOrdersWsImpl() {
		init();
	}

	public void init() {

		Product product = new Product();
		product.setId("1");
		product.setDescription("Phone");
		product.setQuantity(BigInteger.valueOf(12));

		Order order = new Order();
		order.setId(BigInteger.valueOf(1));
		order.getProduct().add(product);

		List<Order> orderList = new ArrayList<>();
		orderList.add(order);

		map.put(BigInteger.valueOf(++customerID), orderList);

	}

	@Override
	public GetOrdersResponse getOrders(GetOrdersRequest request) {
		// in request, we get the customer ID
		// in response we send the List<Order>
		List<Order> list = map.get(request.getCustomerId());
		GetOrdersResponse response = new GetOrdersResponse();
		response.getOrder().addAll(list);
		return response;
	}

	@Override
	public CreateOrdersResponse createOrders(CreateOrdersRequest request) {

		// in request, we get the ID and the order list
		// in response, we send the boolean true false for success or failure.
		List<Order> list = map.get(request.getCustomerId());
		Order order = request.getOrder();
		list.add(order);
		CreateOrdersResponse response = new CreateOrdersResponse();
		response.setResult(true);

		return response;
	}

}
