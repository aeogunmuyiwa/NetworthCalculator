package com.intuit.networthcalculator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssetTypeController {

    private  final AssetTypeService assetTypeService;

    @Autowired
    public AssetTypeController(AssetTypeService assetTypeService) {
        this.assetTypeService = assetTypeService;
    }

    @GetMapping
    public List<AssetType> getAssetTypes(){
        return assetTypeService.getAssetTypes();
    }

    @GetMapping("/assets/{id}")
    ResponseEntity <AssetType> getGroup(@PathVariable String id) {
      AssetType result = assetTypeService.findById(id);
        if (result == null ) {
            return  ResponseEntity.notFound().build();
          //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok().body(result);
        }
    }

    @GetMapping("/addAsset/{name}/{amount}")
    public List<AssetType> createAsset(@PathVariable String name, @PathVariable int amount )   {
        AssetType newAsset = new AssetType();
        newAsset.setName(name);
        newAsset.setAmount((double) amount);
        return assetTypeService.saveAsset(newAsset);
    }

//    @PostMapping("/addAsset/{id}")
//    ResponseEntity<AssetType> updateAsset(@RequestBody AssetType asset )throws URISyntaxException {
//        AssetType result =  assetTypeService.saveAsset(asset);
//        return ResponseEntity.ok().body(result);
//    }

//    @DeleteMapping("/asset/{id}")
//    public ResponseEntity<?> deleteGroup(@PathVariable String id) {
//        assetTypeService.deleteAsset(id);
//        return ResponseEntity.ok().build();
//    }

}
