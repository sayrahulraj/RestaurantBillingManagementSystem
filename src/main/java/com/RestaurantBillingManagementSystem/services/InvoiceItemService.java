package com.RestaurantBillingManagementSystem.services;

import com.RestaurantBillingManagementSystem.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemService {
    List<InvoiceItem> getInvoiceItem();

    InvoiceItem createInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem getInvoiceItemById(int id);

    InvoiceItem updateInvoiceItem(InvoiceItem invoiceItem);

    InvoiceItem deleteInvoiceItemById(int id);
}
