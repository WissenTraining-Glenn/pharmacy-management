import { Route, Routes, BrowserRouter } from "react-router-dom";
import "./App.css";
import HomeLayout from "./pages/HomeLayout";
import OrderPage from "./pages/OrderPage";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Navbar from "./components/Navbar";
import ClientPage from "./pages/ClientPage";
import AddClient from "./components/Client/AddClient";
import ShowClients from "./components/Client/ShowClients";
import AddEmployee from "./components/Employee/AddEmployee";
import ShowEmployees from "./components/Employee/AddEmployee";
import EmployeePage from "./pages/EmployeePage";
import ShowMedicine from "./components/Medicine/ShowMedicines";
import MedicinePage from "./pages/MedicinePage";

// function App() {
// 	const [show, setShow] = useState(true);

// 	return (
// <div className="container">
// 	<Stack spacing={2} direction="row">
// 		<Button variant="outlined" onClick={() => setShow(false)}>
// 			Add Client
// 		</Button>
// 		<Button variant="outlined" onClick={() => setShow(true)}>
// 			All Clients
// 		</Button>
// 	</Stack>

// 	{!show && <AddClient />}

// 	{show && <ShowClient />}
// </div>
// 	);
// }

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<HomeLayout />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          {/* <Route index element={<LandingPage />} />       */}
          <Route path="/order" element={<OrderPage />}>
            {/* <Route path="/:id" element={<OrderDetails />} /> */}
          </Route>
          <Route path="clients" element={<ClientPage />}>
            {/* <Route path="add" element={<AddClient />} />
            <Route path=":id" element={<ShowClients />} /> */}
          </Route>
          <Route path="employees" element={<EmployeePage />}>
            <Route path="add" element={<AddEmployee />} />
            <Route path=":id" element={<ShowEmployees />} />
          </Route>
          <Route path="medicine" element={<MedicinePage />}>
            {/* <Route path="/add" element={<AddMedicine />} /> */}
            {/* <Route path="/:id" element={<ShowMedicine />} /> */}
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
