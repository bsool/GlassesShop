package com.bsool.glasses.glassesshop.data.product.purchase;

import com.bsool.glasses.glassesshop.data.product.frame.Frame;
import com.bsool.glasses.glassesshop.data.product.lens.Lens;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lens_id", nullable = false)
    private Lens lens;
    @Column(name = "lens_cost", nullable = false)
    private float lensCost;
    @ManyToOne
    @JoinColumn(name = "frame_id", nullable = false)
    private Frame frame;
    @Column(name = "frame_cost", nullable = false)
    private float frameCost;
    @CreatedDate
    @Column(name = "purchase_date", updatable = false)
    private LocalDateTime purchase_date;
    @Column(name = "total_cost")
    private float totalCost;
    @Column(name = "currency_code", nullable = false)
    private String currencyCode;

    @PrePersist
    private void onPersist() {
        this.setTotalCost(this.frameCost + this.lensCost);
    }
}
