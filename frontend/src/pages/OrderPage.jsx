import { Button, Stack } from "@mui/material";
import { useState } from "react";
import AddClient from "../components/Client/AddClient";
import ShowClients from "../components/Client/ShowClients";
import ShowOrders from "../components/Order/ShowOrders";
import AddOrder from "../components/Order/AddOrder";

function OrderPage() {
  const [show, setShow] = useState(true);
  return (
    <>
      <div className="container">
        <Stack spacing={2} direction="row">
          <Button variant="outlined" onClick={() => setShow(false)}>
            Add Order
          </Button>
          <Button variant="outlined" onClick={() => setShow(true)}>
            All Orders
          </Button>
        </Stack>

        {!show && <AddOrder />}

        {show && <ShowOrders />}
      </div>
    </>
  );
}

export default OrderPage;
