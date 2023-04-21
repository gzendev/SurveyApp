import {AppBar, Button, Toolbar, Typography} from "@material-ui/core";
import React from "react";
import {withRouter} from 'react-router'

const Header = (props) => {
    return (
        <AppBar position="static">
            <Toolbar>
                <Typography paragraph variant="h5" style={{margin: "0 10px"}}>SurveyApp</Typography>
                <Button
                    color="inherit"
                    onClick={() => {
                        props.history.push("/surveys")
                    }}
                    style={{margin: "0px 10px"}}
                >Surveys</Button>
                <Button
                    color="inherit"
                    onClick={() => {
                        props.history.push("/members")
                    }}
                    style={{margin: "0px 10px"}}
                >Members</Button>
                <Button
                    color="inherit"
                    onClick={() => {
                        props.history.push("/respondents")
                    }}
                    style={{margin: "0px 10px"}}
                >Respondents</Button>
                <Button
                    color="inherit"
                    onClick={() => {
                        props.history.push("/invitables")
                    }}
                    style={{margin: "0px 10px"}}
                >Invitables</Button>
            </Toolbar>
        </AppBar>
    )
}

export default withRouter((Header));