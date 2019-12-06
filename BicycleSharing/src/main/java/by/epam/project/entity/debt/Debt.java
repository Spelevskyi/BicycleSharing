package by.epam.project.entity.debt;

import java.math.BigDecimal;
import java.sql.Date;

public class Debt {

    private int id;
    private int debtorId;
    private BigDecimal amount;
    private Date creationDate;

    public Debt(int debtorId, BigDecimal amount, Date creationDate) {
        super();
        this.debtorId = debtorId;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(int debtorId) {
        this.debtorId = debtorId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + debtorId;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Debt other = (Debt) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (debtorId != other.debtorId)
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Debt [id=" + id + ", debtorId=" + debtorId + ", amount=" + amount + ", creationDate=" + creationDate
                + "]";
    }
}
