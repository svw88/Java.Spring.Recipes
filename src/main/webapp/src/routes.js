import Home from "layouts/home";
import Login from "layouts/login";
import Profile from "layouts/profile";
import UserContext from "components/UserContext";

import Icon from "@mui/material/Icon";

const routes = [
      {
        name: "account",
        icon: <Icon>person</Icon>,
        requiresLogin: true,
        collapse: [
          {
            name: "profile",
            route: "/profile",
            component: <Profile />,
          },
          {
            name: "sign out",
            route: "/home",
            component: <Home />,
            handleClick: () => UserContext.isLoggedIn.dispatch({type: false}),
          }
        ],
      },
      {
        name: "sign in",
        icon: <Icon>login</Icon>,
        route: "/sign-in",
        requiresLogin: false,
        component: <Login />
      }
];

export default routes;
