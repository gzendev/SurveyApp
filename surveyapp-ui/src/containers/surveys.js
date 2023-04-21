import React, {useEffect, useState} from "react";
import {withRouter} from 'react-router'
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Table from "@material-ui/core/Table";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import axios from "axios";

const Surveys = (props) => {
    const [data, setData] = useState([]);
  
    useEffect(() => {
        axios.get("http://localhost:8080/api/surveys/statistics")
            .then((res) => {
                setData(res.data);
                console.log("Result:", data);
            })
            .catch((error) => {
                console.log(error);
            });
    }, []);

    return (
        <div style={{ width: '100%', padding: 25 }}>
        <Table aria-label="simple table" stickyHeader style={{ width: '70%', margin: 'auto' }}>
            <TableHead>
              <TableRow>
                <TableCell>Survey ID</TableCell>
                <TableCell align="right">Survey Name</TableCell>
                <TableCell align="right">Completes</TableCell>
                <TableCell align="right">Filtered Participants</TableCell>
                <TableCell align="right">Rejected Participants</TableCell>
                <TableCell align="right">Average Time Spent</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {data.map((row) => (
                <TableRow key={row.surveyId}>
                  <TableCell component="th" scope="row">
                    {row.surveyId}
                  </TableCell>
                  <TableCell align="right">{row.surveyName}</TableCell>
                  <TableCell align="right">{row.completedParticipants}</TableCell>
                  <TableCell align="right">{row.filteredParticipants}</TableCell>
                  <TableCell align="right">{row.rejectedParticipants}</TableCell>
                  <TableCell align="right">{(Math.round(row.avgTimeSpent * 100) / 100).toFixed(2)}</TableCell>
                </TableRow>
              ))}
            </TableBody>
            </Table>      
      </div>
    );
}

export default withRouter(Surveys);