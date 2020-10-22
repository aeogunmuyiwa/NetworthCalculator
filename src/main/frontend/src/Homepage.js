import React, {Component} from "react";
import AssetsMain from "./Assets/AssetsMain";
import LiabilityHome from "./Liability/LiabilityMain";
import {Button, Container, Table} from "reactstrap";
import {GetNetworth} from "./APis";
class Assets extends Component{
    constructor(props) {
        super(props);
        this.state = {Total : ''}
    }
    componentDidMount() {
        fetch(GetNetworth)
            .then(response => response.json())
            .then(
                data => this.setState({Total : data.totalNetworth})
            )
    }
    fetchdata = (prevProps, prevState) => {
        this.componentDidMount()
    };


    render() {
        return (
            <div>
                <Container fluid>
                    <div color="success"  className="float-right">${this.state.Total} </div>
                    <h5>Net Worth</h5>
                </Container>

               <AssetsMain fetchdata = {this.fetchdata}/>
               <LiabilityHome fetchdata = {this.fetchdata}/>
            </div>
        )
    }

}
export default Assets;