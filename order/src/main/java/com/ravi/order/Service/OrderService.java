package com.ravi.order.Service;

import java.util.List;
import org.springframework.web.client.RestClient;

import com.ravi.order.VO.ProdukVO;
import com.ravi.order.VO.PelangganVO;
import com.ravi.order.VO.OrderResponseVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.order.Entity.Order;
import com.ravi.order.Repository.OrderRepository;

@Service
public class OrderService {

    private final RestClient restClient;
    @Autowired
    private OrderRepository orderRepository;

    OrderService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Long id, Order order) {
        Order existing = orderRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setProduk_id(order.getProduk_id());
            existing.setPelanggan_id(order.getPelanggan_id());
            existing.setTgl_trans(order.getTgl_trans());
            existing.setJumlah(order.getJumlah());
            existing.setTotal(order.getTotal());
            return orderRepository.save(existing);
        }
        return null;
    }

    public PelangganVO getPelanggan(Long id){
        return restClient.get()
            .uri("http://localhost:8081/api/pelanggan/" + id)
            .retrieve()
            .body(PelangganVO.class);
    }

    public ProdukVO getProduk(Long id){
        return restClient.get()
            .uri("http://localhost:8080/api/produk/" + id)
            .retrieve()
            .body(ProdukVO.class);
    }

    public OrderResponseVO getOrderDetail(Long id) {

        Order order = orderRepository
                .findById(id)
                .orElse(null);

        if (order == null) {
            return null;
        }

        ProdukVO produk = getProduk(order.getProduk_id());

        PelangganVO pelanggan = getPelanggan(order.getPelanggan_id());

        return new OrderResponseVO(
                order,
                produk,
                pelanggan);
    }

}