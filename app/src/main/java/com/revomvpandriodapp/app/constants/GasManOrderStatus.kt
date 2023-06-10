package com.revomvpandriodapp.app.constants

import kotlin.String

public object GasManOrderStatus {
    public val NEWORDER: String = "New Order"

    public val PENDINGPICKUP: String = "Pending PickUp"

    public val PENDINGDELIVERY: String = "Pending Delivery"

    public val DELIVERED: String = "Delivered"

    public val COMPLETED: String = "Completed"

    public val CANCELLED: String = "Cancelled"

    public val UNKNOWN: String = "UnKnown"
}
