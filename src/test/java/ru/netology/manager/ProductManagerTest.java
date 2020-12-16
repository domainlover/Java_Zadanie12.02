package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class ProductManagerTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    ProductManager productManager;

    Product one = new Smartphone(1, "Apple", 100000, "USA");
    Product two = new Smartphone(2, "Google", 75000, "USA");
    Product three = new Book(3, "novel", 1000, "Gogol");
    Product four = new Book(4, "novel", 1000, "Dostoevsky");

    @BeforeEach
    public void setUp() {
        productManager.add(one);
        productManager.add(two);
        productManager.add(three);
        productManager.add(four);
    }

    @Test
    public void shouldSearchBySmartphoneName() {
        Product[] returned = new Product[]{one, two, three, four};
        doReturn(returned).when(productRepository).findAll();
        Product[] expected = new Product[]{one};
        Product[] actual = productManager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBySmartphoneProducer() {
        Product[] returned = new Product[]{one, two, three, four};
        doReturn(returned).when(productRepository).findAll();
        Product[] expected = new Product[]{one, two};
        Product[] actual = productManager.searchBy("USA");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByBookAuthor() {
        Product[] returned = new Product[]{one, two, three, four};
        doReturn(returned).when(productRepository).findAll();
        Product[] expected = new Product[]{four};
        Product[] actual = productManager.searchBy("Dostoevsky");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByBookGenre() {
        Product[] returned = new Product[]{one, two, three, four};
        doReturn(returned).when(productRepository).findAll();
        Product[] expected = new Product[]{three, four};
        Product[] actual = productManager.searchBy("novel");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEmpty() {
        Product[] returned = new Product[]{one, two, three, four};
        doReturn(returned).when(productRepository).findAll();
        Product[] expected = new Product[]{};
        Product[] actual = productManager.searchBy("China");
        assertArrayEquals(expected, actual);
    }
}

