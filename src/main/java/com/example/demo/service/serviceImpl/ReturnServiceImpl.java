@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private ReturnRepository returnRepository;

    public Long createReturn(Long orderId, String reason) {
        Return returnOrder = new Return();
        returnOrder.setOrder(new Order(orderId));
        returnOrder.setReturnDate(LocalDateTime.now());
        returnOrder.setStatus("Pending");
        returnOrder.setReason(reason);
        returnRepository.save(returnOrder);
        return returnOrder.getReturnId();
    }

    public void processReturn(Long returnId, String status) {
        Return returnOrder = returnRepository.findById(returnId)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        returnOrder.setStatus(status);
        if ("Completed".equals(status)) {
            returnOrder.setCompletedDate(LocalDateTime.now());
        }
        returnRepository.save(returnOrder);
    }

    public String checkReturnStatus(Long returnId) {
        Return returnOrder = returnRepository.findById(returnId)
                .orElseThrow(() -> new RuntimeException("Return not found"));
        return returnOrder.getStatus();
    }
}
