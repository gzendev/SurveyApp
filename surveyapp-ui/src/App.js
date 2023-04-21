import React, {useEffect} from "react";
import {Route, Switch} from "react-router-dom";
import {Redirect, withRouter} from 'react-router'
import Header from "./components/header"
import Survey from "./containers/surveys"
import Member from "./containers/members";
import Respondents from "./containers/respondents";
import Invitables from "./containers/invitables";

const App = (props) => {

  useEffect(() => {
      if (!props.isAuthenticated && (window.location.pathname === "/change_password" && window.location.pathname === "/verify")) {
          props.history.push("/login")
      }
  }, []);

  return (
      <div className="App">
          <Header/>
          <Switch>
            <Route exact path="/invitables" component={Invitables}/>
            <Route exact path="/respondents" component={Respondents}/>
            <Route exact path="/surveys" component={Survey}/>
            <Route exact path="/members" component={Member}/>
            <Redirect to={"/surveys"}/>
          </Switch>
      </div>
  );
}

export default withRouter((App));