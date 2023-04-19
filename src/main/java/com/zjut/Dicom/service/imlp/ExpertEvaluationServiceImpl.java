package com.zjut.Dicom.service.imlp;

import com.zjut.Dicom.mapper.ExpertEvaluationMapper;
import com.zjut.Dicom.mapper.PatientMapper;
import com.zjut.Dicom.mapper.ProjectExpertMapper;
import com.zjut.Dicom.mapper.StudyMapper;
import com.zjut.Dicom.pojo.ExpertEvaluation;
import com.zjut.Dicom.pojo.ProjectExpert;
import com.zjut.Dicom.pojo.Study;
import com.zjut.Dicom.pojo.VO.PatientExpVO;
import com.zjut.Dicom.service.ExpertEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpertEvaluationServiceImpl implements ExpertEvaluationService {

    @Autowired
    private ExpertEvaluationMapper expertEvaluationMapper;

    @Autowired
    private StudyMapper studyMapper;

    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private ProjectExpertMapper projectExpertMapper;

    /**
     * 查询---根据专家id和状态 status
     * @param expertId
     * @param status
     * @return
     */
    @Override
    public List<PatientExpVO> getByExpertIdAndStatus(Integer expertId,Byte status) {
        List<ExpertEvaluation> expertEvaluations = expertEvaluationMapper.getByExpertIdAndStatus(expertId,status);
        List<PatientExpVO> finalList = new ArrayList<>();
        ArrayList<ExpertEvaluation> collect = expertEvaluations.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(
                Comparator.comparing(ExpertEvaluation::getPatientId)
        )), ArrayList::new));
        for (ExpertEvaluation expertEvaluation : collect) {
            PatientExpVO patientExpVO = patientMapper.getByPatientId(expertEvaluation.getPatientId());
            patientExpVO.setStudyList(studyMapper.getByPatientId(expertEvaluation.getPatientId()));
            finalList.add(patientExpVO);
        }
        return finalList;
    }


    /**
     * 专家提交报告
     * @param expertEvaluation
     * @return
     */
    @Override
    public int pushEvaluation(ExpertEvaluation expertEvaluation) {
        ExpertEvaluation ee = expertEvaluationMapper.getByExpertIdAndStudyId(expertEvaluation.getExpertId(), expertEvaluation.getStudyId());
        ee.setEvaluateTime(new Date());
        ee.setStatus((byte)1); //状态变为1已评估
        ee.setValidStatus(expertEvaluation.getValidStatus());
        ee.setTargetFocus(expertEvaluation.getTargetFocus());
        ee.setNonTargetFocus(expertEvaluation.getNonTargetFocus());
        ee.setNewFocus(expertEvaluation.getNewFocus());
        ee.setSupplementary(expertEvaluation.getSupplementary());
        ee.setReport(expertEvaluation.getReport());
        int result = expertEvaluationMapper.update(ee);
        //专家评估完后，需要继续判定专家类型，以及判定是否改变study的状态为5评估结束
        if(ee.getExpertType() == 1){
            //该专家为初评专家
            //根据studyId和专家类型1初评专家进行查询
            List<ExpertEvaluation> expertEvaluations = expertEvaluationMapper.getByStudyIdAndExpertType(expertEvaluation.getStudyId(), (byte) 1);
            Byte targetFocus = expertEvaluations.get(0).getTargetFocus();
            Byte nonTargetFocus = expertEvaluations.get(0).getNonTargetFocus();
            Byte newFocus = expertEvaluations.get(0).getNewFocus();
            Byte supplementary = expertEvaluations.get(0).getSupplementary();
            for (ExpertEvaluation expertEva : expertEvaluations) {
                if(expertEva.getStatus() == 0){
                    //表明还有专家对于该study没有评估完，不作任何操作。
                    return result;
                }else {
                    //************表明专家都对该study评估完，进行评估比较。
                    boolean consistent = (targetFocus != expertEva.getTargetFocus()) || (nonTargetFocus != expertEva.getNonTargetFocus())||
                            (newFocus != expertEva.getNewFocus()) || (supplementary != expertEva.getSupplementary());
                    if(consistent){
                        //说明需要新分配终审专家
                        //1.通过studyId，从study表中找到projectId和patientId
                        Study finalStudy = studyMapper.getById(expertEvaluation.getStudyId());
                        //2.通过projectId，从project_expert找到该项目的相关的终审专家expertId
                        ProjectExpert finalProjectExpert = projectExpertMapper.selectFinalExpertByProjectId(finalStudy.getProjectId());
                        //3.设置ExpertEvaluation表中字段projectId, patientId, studyId, expertId, expertType=2, status=0
                        ExpertEvaluation expertEvaluationInsert = new ExpertEvaluation(
                                finalStudy.getProjectId(),
                                finalStudy.getPatientId(),
                                finalStudy.getId(),
                                finalProjectExpert.getExpertId(),
                                (byte)2,
                                (byte)0
                        );
                        result = expertEvaluationMapper.save(expertEvaluationInsert);
                        return result;
                    }
                }
            }
        //能到达此行，表明评估结果一致，不需要终审专家，直接改变为study状态为4阶段结束
        }
        Study study = studyMapper.getById(expertEvaluation.getStudyId());
        study.setStatus((byte)4);
        result = studyMapper.update(study);
        return result;
    }

}
