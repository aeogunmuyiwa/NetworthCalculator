import React, {Component} from "react";
import {Button, Container, Table} from 'reactstrap';
import AddItem from "./LiabilityModal";
import ModalManger from './LiabilityModal'
import CashAndInvestmentsList from "../main/CashAndInvestmentsList";
import {GetNetworth, ListLiabilityApi} from "../APis";


/**
 * section to show asset name , amount , edit and delete functionality
 * */


class LiabilityMain extends Component{
    constructor(props) {
        super(props);
        this.state = { show: false, List : [],isLoading: true, Total : ''}
    }
    Assetmodal = {
        name : '',
        amount: '',
        id : ''
    }
    componentDidMount() {
        this.setState({isLoading: true});
        fetch(ListLiabilityApi)
            .then(response => response.json())
            .then(data => this.setState({List: data, isLoading: false}))
        fetch(GetNetworth)
            .then(response => response.json())
            .then(
                data => this.setState({Total : data.networth[1].sum})
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
                    <AddItem show={this.state.show} handleClose={this.hideModal} assetId = {''} modalId = {'New'} Liability = "Add a new asset" modal = {this.Assetmodal} >
                    </AddItem>
                    <h5> Liability
                    </h5>
                    <Table  striped bordered hover size="sm" className="mt-4" dark>
                        <tbody>
                        {this.renderBody()}
                        </tbody>
                        <Container fluid >
                            <div   style={{whiteSpace: 'pre-wrap', color: 'white' ,wordWrap: 'break-word'}} className="float-right">${this.state.Total} </div>
                            <h5 > Total Liabilities</h5>
                        </Container>
                    </Table>
                </Container>
            </div>

        )
    }
    renderBody = () => {
        const {List, isLoading} = this.state;
        if (isLoading) {
            return ;
        }else{

            return List.map((list , index) => {
                return <tr key={list.assetTypeName}>
                    <td>
                        <CashAndInvestmentsList Title = {list.assetTypeName} list = {list.assetTypeList} category = {index} fetchdata = {this.fetchdata} Modalpopup ={ModalManger} endpoint = {ListLiabilityApi}/>
                    </td>
                </tr>


            })
        }

    }
    

}
export default LiabilityMain;