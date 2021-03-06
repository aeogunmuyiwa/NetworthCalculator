import React, {Component} from "react";
import {Button, Container, Table} from 'reactstrap';
import AddItem from "./AssetModal";
import Modalpopup from './AssetModal'
import  CashAndInvestmentsList from '../main/CashAndInvestmentsList'
import {ListAssetApi,GetNetworth} from "../APis";
/**
 * section to show asset name , amount , edit and delete functionality
 * */


class AssetsMain extends Component{
    constructor(props) {
        super(props);
        this.state = { show: false, List : [],isLoading: true , Total : ''}
    }
    Assetmodal = {
        name : '',
        amount: '',
        id : ''
    }
    componentDidMount() {
        this.setState({isLoading: true});
        fetch(ListAssetApi)
            .then(response => response.json())
            .then(data => this.setState({List: data, isLoading: false}))

        fetch(GetNetworth)
            .then(response => response.json())
            .then(
                data => this.setState({Total : data.networth[0].sum})
            )
    }


    hideModal = () => {
        this.setState({ show: false });
    };

    fetchdata = (prevProps, prevState) => {
        this.componentDidMount()
        this.props.fetchdata()
    };

    render() {

        return (
            <div>
                <Container fluid>
                    <AddItem show={this.state.show} handleClose={this.hideModal} assetId = {''} modalId = {'New'}  modal = {this.Assetmodal}>
                    </AddItem>

                    <h5> Asset </h5>
                    <Table  striped bordered hover size="sm" className="mt-4" dark>
                        <tbody>
                        {this.renderBody()}
                        </tbody>
                        <Container fluid>
                                <div style={{whiteSpace: 'pre-wrap', color: 'white' ,wordWrap: 'break-word'}} className="float-right">${this.state.Total} </div>
                                <h5 > Total Assets</h5>
                        </Container>
                    </Table>
                </Container>
            </div>

        )
    }
    renderBody = () => {
        const {List, isLoading, Total} = this.state;
        if (isLoading) {
            return;
        }else{
            return List.map((list , index) => {
                return <tr key={list.assetTypeName}>
                    <td >
                        <CashAndInvestmentsList  Title = {list.assetTypeName} list = {list.assetTypeList} category = {index} fetchdata = {this.fetchdata} Modalpopup = {Modalpopup} endpoint = {ListAssetApi}/>
                    </td>
                </tr>
            })

        }

    }


}
export default AssetsMain;