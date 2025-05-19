package com.yfk.comfyui.comfyui_dao.dao;

import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_dao.gen.dao.FlowPOMapper;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPO;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPOExample;
import com.yfk.comfyui.comfyui_dao.mapper.FlowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:30
 */
@Repository
public class FlowDao {

    @Autowired
    private FlowPOMapper mapper;

    public FlowDTO searchById(Long id) {
        FlowPO po = mapper.selectByPrimaryKey(id);

        return FlowMapper.INSTANCE.poToDto(po);
    }

    public List<FlowDTO> search(FlowDTO dto) {
        FlowPOExample example = new FlowPOExample();
        FlowPOExample.Criteria criteria = example.createCriteria();
        if (Objects.nonNull(dto.getId())) {
            criteria.andIdEqualTo(dto.getId());
        }
        if (Objects.nonNull(dto.getFlowName())) {
            criteria.andFlowNameLike("%" + dto.getFlowName() + "%");
        }

        List<FlowPO> flowPOS = mapper.selectByExampleWithBLOBs(example);
        return flowPOS.stream().map(FlowMapper.INSTANCE::poToDto).toList();
    }
}
