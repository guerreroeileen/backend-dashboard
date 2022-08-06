package com.dashboard.backenddashboard.common.controller;


import com.dashboard.backenddashboard.common.dto.BaseDto;
import com.dashboard.backenddashboard.common.service.IBaseCRUDService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequiredArgsConstructor
public class BaseCRUDController<D extends BaseDto, I extends Serializable> {

    private final IBaseCRUDService<D, I> iBaseCRUDService;

    /**
     * Get all resources.
     *
     * @return All resources.
     */
    @ApiOperation(value = "Get all resources", code = 200)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<Page<D>> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "false") Boolean paged
    ) {
        Page<D> pagedData = this.iBaseCRUDService.findAll(page, limit, paged);
        return ResponseEntity.ok(pagedData);
    }

    /**
     * Get resource by id.
     *
     * @param id resource id to be retrieved.
     * @return The resource retrieved.
     */
    @ApiOperation(value = "Get all resources by id", code = 200)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}")
    public ResponseEntity<D> getResourceById(@PathVariable() I id) {
        D resource = this.iBaseCRUDService.findById(id);

        return ResponseEntity.ok(resource);
    }

    /**
     * Creates a resource.
     *
     * @param requestEntity The request entity to created.
     * @return The resource created.
     */
    @ApiOperation(value = "Creates a resource", code = 201)
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseEntity<D> createResource(@Valid @RequestBody D requestEntity) {
        D baseEntityCreated = iBaseCRUDService.save(requestEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(baseEntityCreated);
    }

    /**
     * Update resource.
     *
     * @param requestEntity The request entity to updated.
     * @param id            The id to be updated.
     * @return The resource updated.
     */
    @ApiOperation(value = "Update a resource", code = 201)
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "{id}")
    public ResponseEntity<D> updateResource(@Valid @RequestBody D requestEntity, @PathVariable I id) {
        D baseEntityUpdated = iBaseCRUDService.update(requestEntity, id);

        return ResponseEntity.status(HttpStatus.OK).body(baseEntityUpdated);
    }


    /**
     * Deletes a resource of base entity by id.
     *
     * @param id The id of the resource.
     */
    @ApiOperation(value = "Deletes a resource by his id")
    @ApiResponse(code = 404, message = "The resource was not found")
    @DeleteMapping(value = "{id}")
    public void deleteResource(@PathVariable() I id) {
        iBaseCRUDService.delete(id);
    }

}
