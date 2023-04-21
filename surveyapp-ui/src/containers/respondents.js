import React, {useState} from "react";
import {withRouter} from 'react-router'
import axios from 'axios';
import IconButton from "@material-ui/core/IconButton";
import TextField from "@material-ui/core/TextField";
import InputAdornment from "@material-ui/core/InputAdornment";
import SearchIcon from "@material-ui/icons/Search";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Table from "@material-ui/core/Table";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";

const Respondents = (props) => {
    const [APIData, setAPIData] = useState([])
    const [searchInput, setSearchInput] = useState('');

    const search = query => {
        const url = `http://localhost:8080/api/members/completed-surveys/${query}`;
    
        axios.get(url)
           .then((response) => {
            console.log(response.data)
              setSearchInput(query);
              setAPIData(response.data);
           })
    };

    const onChange = (e) => {
        const { value } = e.target;
        if(e.key === 'Enter' && value !== '' && !isNaN(value)) {
            search(value);
        }
    }

    return (
        <div>
        <div style={{ width: '100%', padding: 25 }}>
            <TextField
                label="Search Respondents"
                onKeyUp={onChange}
                InputProps={{
                    endAdornment: (
                    <InputAdornment position="start">
                        <IconButton onClick={onChange}>
                        <SearchIcon />
                        </IconButton>
                    </InputAdornment>
                    )
                }}
            />
        </div>
        {(searchInput.length > 0) ?
                            APIData !== null ? 
                            <div style={{
                                display: "flex",
                                alignItems: "center",
                                height: "100%"
                              }}>
                                <Table aria-label="simple table" stickyHeader style={{ width: '70%', margin: 'auto' }}>
                                <TableHead>
                                <TableRow>
                                    <TableCell>Member Id</TableCell>
                                    <TableCell align="right">Full name</TableCell>
                                    <TableCell align="right">E-mail</TableCell>
                                    <TableCell align="right">Active</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                    {APIData.map((row) => (
                                        <TableRow key={row.id}>
                                            <TableCell component="th" scope="row">
                                            {row.id}
                                            </TableCell>
                                            <TableCell align="right">{row.fullname}</TableCell>
                                            <TableCell align="right">{row.email}</TableCell>
                                            <TableCell align="right">{row.active}</TableCell>
                                        </TableRow>
                                    ))}
                                </TableBody>
                                </Table>
                            </div>
                            : <div style={{display: "flex", alignItems: "center", height: "100%"}}>Member not found</div>
                            : <div></div>}
                            </div>
    )

}

export default withRouter(Respondents);
